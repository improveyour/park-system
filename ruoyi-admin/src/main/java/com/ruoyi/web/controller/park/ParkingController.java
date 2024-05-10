package com.ruoyi.web.controller.park;


import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.ruoyi.parkingCost.domain.PayInfo;
import com.ruoyi.parkingCost.service.IPayInfoService;
import com.ruoyi.parkingHistory.domain.ParkingRecordInfo;
import com.ruoyi.parkingHistory.service.IParkingRecordInfoService;
import com.ruoyi.parkingInfo.domain.ParkingInfo;
import com.ruoyi.parkingInfo.service.IParkingInfoService;
import com.ruoyi.plateRec.service.IFileService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ParkingController {

    @Autowired
    IFileService fileService;

    @Autowired
    IParkingInfoService parkingInfoService;

    @Autowired
    IParkingRecordInfoService parkingRecordInfoService;

    @Autowired
    IPayInfoService payInfoService;

    private static String fileLocation = "E:/graduate/park-system/ruoyi-admin/src/main/resources/plate_img/";


    //车辆入库
    @PostMapping("/park/imgSave")
    public @ResponseBody Map<String, Object> saveImage(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) {

        String plate = null;
        //返回前端的json
        Map<String, Object> map = new HashMap<>();

        //将图片保存到服务器的目录下，并重命名，防止冲突
        String fileName = fileService.saveFile(files[0]);

        System.out.println("新文件名为==============================>" + fileName);

        // todo 应该调用模型来识别车牌信息，根据识别结果来重命名图片
        plate = fileService.recPlateByFileName(files[0], fileName);
        if (plate == "识别失败") {
            map.put("status", 500);
            map.put("msg", "识别失败！");
            return map;
        }
        System.out.println(plate);

        //获取当前时间
        Date nowDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = format.format(nowDate);

        //将车牌号随机赋值给一个车位，也就是修改车位信息表（parking_info）
        //返回停车位的编号
        Long parkingId = parkingInfoService.carPark(plate);
        // 如果为空，那就是停车场满了，直接拒绝入库
        if (parkingId == null) {
            map.put("status", 200);
            map.put("msg", "很抱歉，停车场已经没有空闲车位！");
            return map;
        }

        System.out.println(parkingId);

        // 根据停车位的编号来获取这个停车位的位置
        ParkingInfo parkingInfo = parkingInfoService.selectParkingInfoById(parkingId);

        //在停车记录表中插入一条信息，格式如下：
        // id = 随机生成
        // pay = -1 表示没有收费
        // parkingTime = 入库时间
        // leaveTime = 出库时间 = 入库时间 （没有车辆能够做到同时出库和入库）
        // payTime = 支付时间 = 入库时间（在尚未缴费的情况下，出库时间应该要 = 入库时间 = 缴费时间）
        // carPlate = 车牌号
        // parkingId = 车位编号
        // 如果这三个时间都相等，说明车辆刚开始入库
        // 如果车辆要出库的是偶，出库时间必然比入库时间要晚，并且要等于缴费时间
        // 表示只有缴费成功之后才能出库
        ParkingRecordInfo recordInfo = new ParkingRecordInfo();
        recordInfo.setPay(-1); //表示还没付款
        recordInfo.setPayStatus("0"); //表示还没付款
        recordInfo.setParkingId(parkingId); //车位编号
        recordInfo.setCarPlate(plate);
        recordInfo.setPositions(parkingInfo.getPosition());

        // 在未出库前，出库时间、入库时间、支付时间都默认一致
        recordInfo.setParkingTime(currentTime);
        recordInfo.setLeaveTime(currentTime);
        recordInfo.setPayTime(currentTime);

        // 插入到停车记录表
        int i = parkingRecordInfoService.insertParkingRecordInfo(recordInfo);
        System.out.println("受影响的行数为 =======> " + i);


        map.put("status", 200);
        map.put("msg", "车辆入库成功！时间为：" + format.format(nowDate));
        return map;
    }


    //车辆出库
    @PostMapping("/park/imgDelete")
    public @ResponseBody Map<String, Object> deleteImage(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) {


        // 得到上传文件后缀
        String originalName = files[0].getOriginalFilename();
        String ext = "." + FilenameUtils.getExtension(originalName);

        // todo 根据图片识别车牌号
        String plate = fileService.recPlate(files[0].getOriginalFilename());

        String target = plate + ext;

        System.out.println("车牌识别结果============> " + plate);

        // 根据车牌号在指定文件夹里面查找重名的图片
        boolean b = fileService.deleteFile(target);

        //返回前端的json
        Map<String, Object> map = new HashMap<>();

        if (b == true) {
            // 找到了就进行出库操作
            // 1.获取当前时间
            Date nowDate = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = format.format(nowDate);

            // 2.从停车记录表中获取要出库的车牌号，入库时间
            // 由于是通过车牌号码查询停车记录表，所以有概率是会出现同一个车牌号码，因为一辆车可能同一天停了两次
            // 但是同一个车牌号码且处于未出库未支付的情况下肯定只有一个，不能两辆相同号码的车同时停在停在停车场
            // 的两个不同车位吧

            // 因此要查找：未出库，未支付，且指定车牌号的车辆停放信息，也就是 pay = -1 && status = 0 && plate = ....
            ParkingRecordInfo recordInfo = parkingRecordInfoService.selectParkingRecordInfoByPlateAndStatus(plate, -1, 0);

            System.out.println(recordInfo);

            String outputCarPlate = recordInfo.getCarPlate();
            System.out.println("出库操作 ========> " + outputCarPlate);

            String parkingTime = recordInfo.getParkingTime();

            // 3.计算停车时长在哪个时间区段，根据时间区段计算费用
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //24小时制
            Date date1 = null;
            try {
                date1 = simpleDateFormat.parse(parkingTime);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            long time1 = date1.getTime();
            // 获取停车时长(单位：秒)
            Long totalParkingTime = System.currentTimeMillis() / 1000 - time1 / 1000;
            System.out.println("车辆入库时间为：==========> " + parkingTime + " 秒");
            System.out.println("车辆出库时间为：==========> " + currentTime + " 秒");
            System.out.println("停车时间为：==========> " + totalParkingTime + " 秒");

            // 获取收费时间区段
            //   parking_time   cost
            //       0            0
            //       2            5
            //       4            10
            //       6            20
            // 收费区段一共分为五个，(0,2),(2,4),(4,6),(6,+∞)
            //                收费：  0     5    10     20
            List<PayInfo> all = payInfoService.getAll();
            long[] arrayParkingTime = new long[all.size()];
            int[] arrayCost = new int[all.size()];
            int i = 0;
            for (PayInfo temPayInfo : all) {
                arrayParkingTime[i] = (long) temPayInfo.getParkingTime();
                arrayCost[i] = temPayInfo.getCost();
                i++;
            }
            int cost = 0;
            int j, k;
            for (j = 1, k = 0; j < i; j++, k++) {
                if (totalParkingTime <= arrayParkingTime[j] * 3600) {
                    cost = arrayCost[k];
                    break;
                }
            }
            // 达到计费最大区间
            if (j == i && k == i - 1) {
                cost = arrayCost[k];
            }
            System.out.println("需要支付的金额为===============>" + cost);

            // 不需要用户支付，直接将信息写入停车记录表即可
            if (cost == 0) {
                // 获取当前时间
                Date now_Date = new Date();
                SimpleDateFormat format_pay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime_pay = format.format(now_Date);

                // 6.更新停车记录表中的支付金额，出库时间，缴费时间，支付状态
                recordInfo.setPay(cost);
                recordInfo.setPayStatus("1");
                recordInfo.setLeaveTime(currentTime_pay);
                recordInfo.setPayTime(currentTime_pay);
                parkingRecordInfoService.updateParkingRecordInfo(recordInfo);

                // 4.修改车位表中对应的车位编号的状态为 0，车牌号为空，表示车辆已经离开
                ParkingInfo parkingInfo = parkingInfoService.updateParkingInfoByPlate(plate);
                parkingInfo.setStatus(0);
                parkingInfo.setCarPlate("");
                parkingInfoService.updateParkingInfo(parkingInfo);

                // 把秒转换为时分秒形式，方便用户观察
                int h = (int) (totalParkingTime / 3600);
                int m = (int) ((totalParkingTime - h * 3600) / 60);
                int s = (int) (totalParkingTime - h * 3600 - m * 60);
                String time = "%02d:%02d:%02d";
                time = String.format(time, h, m, s);

                map.put("status", 200);
                map.put("msg", "停车时间为：" + time + ",不需要支付,祝您一路平安！");
                map.put("cost", cost);

                return map;
            }

            // 把这个金额写进停车记录表中的 pay 字段，但是要以负数形式
            // 这样可以充当订单表来使用，方便后续对支付金额进行校验
            recordInfo.setPay(-1 * cost);

            parkingRecordInfoService.updateParkingRecordInfo(recordInfo);

            // 把秒转换为时分秒形式，方便用户观察
            int h = (int) (totalParkingTime / 3600);
            int m = (int) ((totalParkingTime - h * 3600) / 60);
            int s = (int) (totalParkingTime - h * 3600 - m * 60);
            String time = "%02d:%02d:%02d";
            time = String.format(time, h, m, s);

            map.put("status", 200);
            map.put("msg", "停车时间为：" + time + ",需要支付 " + cost + " 元，请扫码支付！");
            map.put("cost", cost);

            // 进行支付操作
            // 根据金额生成二维码并显示在前端页面
            // 生成 cost 对应的付款二维码到文件，宽和高都是300像素
            String qrCodeParam = String.valueOf(cost) + "&plate=" + plate;
            QrCodeUtil.generate(qrCodeParam, 400, 400, FileUtil.file("C:\\Users\\Administrator\\Desktop\\pay_" + cost + ".png"));

            return map;
        } else {

            System.out.println("该车并未停在停车场");
            map.put("status", 404);
            map.put("msg", "车辆出库失败！");
            return map;
        }
    }

    @GetMapping("/park/pay")
    public String paySuccess(@RequestParam("money") String money, @RequestParam("plate") String plate) {

        System.out.println("从小程序传过来的参数 ===============> " + money);
        System.out.println("从小程序传过来的参数 ===============> " + plate);

        // 收到由微信小程序发过来的付款请求,校验金额是否正确
        ParkingRecordInfo recordInfo = parkingRecordInfoService.selectParkingRecordInfoByPlateAndStatus(plate, -1 * Integer.valueOf(money), 0);
        if (recordInfo == null) {
            System.out.println("未找到对应的车牌号");
            return "未找到对应的车牌号！";
        }

        System.out.println("待支付金额为=========> " + recordInfo.getPay() * -1);
        // 支付金额相等
        if (String.valueOf(recordInfo.getPay() * -1).equals(money)) {
            // 4.修改车位表中对应的车位编号的状态为 0，车牌号为空，表示车辆已经离开
            ParkingInfo parkingInfo = parkingInfoService.updateParkingInfoByPlate(plate);
            parkingInfo.setStatus(0);
            parkingInfo.setCarPlate("");
            int i = parkingInfoService.updateParkingInfo(parkingInfo);
            System.out.println("修改车位表中对应的车位编号后受影响的行数==========>" + i);

            // 支付成功后获取当前时间
            Date nowDate = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = format.format(nowDate);

            // 6.更新停车记录表中的支付金额，出库时间，缴费时间，支付状态
            Integer finalMoney = recordInfo.getPay() * -1;
            recordInfo.setPay(finalMoney);
            recordInfo.setPayStatus("1");
            recordInfo.setLeaveTime(currentTime);
            recordInfo.setPayTime(currentTime);

            int i1 = parkingRecordInfoService.updateParkingRecordInfo(recordInfo);
            System.out.println("更新停车记录表成功========> " + i1);

            return "支付成功，祝您一路平安！";

        } else {
            return "支付金额错误，请重新扫码支付！";
        }

    }


}
