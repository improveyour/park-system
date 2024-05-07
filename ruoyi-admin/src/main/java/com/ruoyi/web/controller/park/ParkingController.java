package com.ruoyi.web.controller.park;


import com.ruoyi.parkingHistory.domain.ParkingRecordInfo;
import com.ruoyi.parkingHistory.service.IParkingRecordInfoService;
import com.ruoyi.parkingInfo.domain.ParkingInfo;
import com.ruoyi.parkingInfo.service.IParkingInfoService;
import com.ruoyi.plateRec.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ParkingController {

    @Autowired
    IFileService fileService;

    @Autowired
    IParkingInfoService parkingInfoService;

    @Autowired
    IParkingRecordInfoService parkingRecordInfoService;

    private static String fileLocation = "E:\\graduate\\park-system\\ruoyi-admin\\src\\main\\resources\\plate_img";


    //车辆入库
    @PostMapping("/park/imgSave")
    public @ResponseBody Map<String, Object> saveImage(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) {

        String plate = null;
        //将图片保存到服务器的目录下，并重命名，防止冲突
        String fileName = fileService.saveFile(files[0]);

        // todo 应该调用模型来识别车牌信息，根据识别结果来重命名图片
        plate = fileService.recPlate(files[0], fileName);
        System.out.println(plate);


        //获取当前时间
        Date nowDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = format.format(nowDate);

        //将车牌号随机赋值给一个车位，也就是修改车位信息表（parking_info）
        //返回停车位的编号
        Long parkingId = parkingInfoService.carPark(plate);
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
        recordInfo.setParkingId(String.valueOf(parkingId)); //车位编号

        // 在未出库前，出库时间、入库时间、支付时间都默认一致
        recordInfo.setParkingTime(currentTime);
        recordInfo.setLeaveTime(currentTime);
        recordInfo.setPayTime(currentTime);
        recordInfo.setCarPlate(plate);
        recordInfo.setPositions(parkingInfo.getPosition());

        // 插入到停车记录表
        int i = parkingRecordInfoService.insertParkingRecordInfo(recordInfo);
        System.out.println("受影响的行数为 =======> " + i);


        //返回前端的json
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("msg", "车辆入库成功！时间为：" + format.format(nowDate));
        return map;
    }


    //车辆出库
    @PostMapping("/park/imgDelete")
    public @ResponseBody Map<String, Object> deleteImage(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) {

        System.out.println(files[0].getOriginalFilename());

        // todo 根据图片识别车牌号
        String plate = fileService.recPlate(files[0], "aaa");

        // 根据车牌号在指定文件夹里面查找重名的图片

        // 找到了就进行出库操作
        // 1.获取当前时间
        Date nowDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = format.format(nowDate);

        // 2.从停车记录表中获取入库时间，车位编号

        // 3.计算停车时长在哪个时间区段，根据时间区段计算费用

        // 4.修改车位表中对应的车位编号的状态为 0，表示车辆已经离开

        // 5.进行支付操作，支付成功后获取当前时间

        // 6.更新停车记录表


        //返回前端的json
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("msg", "车辆出库成功！时间为：" + currentTime);
        return map;
    }


}
