package com.ruoyi.web.controller.park;

import com.ruoyi.park.domain.UserInfo;
import com.ruoyi.park.service.IUserInfoService;
import com.ruoyi.parkingHistory.domain.ParkingRecordInfo;
import com.ruoyi.parkingHistory.service.IParkingRecordInfoService;
import com.ruoyi.parkingInfo.domain.CarPlateInfo;
import com.ruoyi.parkingInfo.service.ICarPlateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WxController {

    @Autowired
    IUserInfoService userInfoService;

    @Autowired
    ICarPlateInfoService carPlateInfoService;

    @Autowired
    IParkingRecordInfoService parkingRecordInfoService;

    /**
     * 获取信息
     *
     * @param username
     * @param password
     * @param phone
     * @return
     */
    @GetMapping("/park/wxGetInfo")
    public String getInfo(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("phone") String phone) {


        System.out.println("username======>" + username);
        System.out.println("password======>" + password);
        System.out.println("phone=========>" + phone);

        //18620703096
        UserInfo userInfo = userInfoService.selectUserInfoByPhone(phone);

        String message = "username&" + userInfo.getUsername() + "&password&" + userInfo.getPassword() + "&phone&" + userInfo.getPhone();
        return message;
    }


    /**
     * 修改信息
     *
     * @param username
     * @param password
     * @param phone
     * @return
     */
    @GetMapping("/park/wxChangeInfo")
    public String changeInfo(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("phone") String phone) {

        UserInfo userInfo = userInfoService.selectUserInfoByPhone(phone);
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        userInfo.setPhone(phone);

        userInfoService.updateUserInfo(userInfo);

        String message = "username&" + username + "&password&" + password + "&phone&" + phone;
        System.out.println(message);
        return message;
    }

    /**
     * 查询指定用户绑定的所有车牌
     *
     * @param phone
     * @return
     */
    @GetMapping("/park/wxGetAllPlateByPhone")
    public List<CarPlateInfo> getAllPlateByPhone(@RequestParam("phone") String phone) {
        List<CarPlateInfo> carPlateInfos = carPlateInfoService.selectCarPlateInfoByPhone(phone);
        return carPlateInfos;
    }

    /**
     * 更换绑定的车牌
     *
     * @param phone
     * @param plate
     * @return
     */
    @GetMapping("/park/wxBindPlate")
    public String changePlate(@RequestParam("phone") String phone, @RequestParam("plate") String plate) {

        System.out.println("phone = " + phone + "    plate = " + plate);

        // 通过电话号码找到指定的用户信息
        UserInfo userInfo = userInfoService.selectUserInfoByPhone(phone);

        CarPlateInfo carPlateInfo = new CarPlateInfo();
        carPlateInfo.setCarPlate(plate);
        carPlateInfo.setUserId(userInfo.getId());
        // 插入到车牌表
        int i = carPlateInfoService.insertCarPlateInfo(carPlateInfo);


        if (i > 0) {
            System.out.println("绑定车牌成功");
            return "success";
        } else {
            return "false";
        }

    }

    /**
     * 查询缴费记录
     *
     * @param phone
     * @param plate
     * @return
     */
    @GetMapping("/park/wxGetHistory")
    public String getHistory(@RequestParam("phone") String phone, @RequestParam("plate") String plate) {


        ParkingRecordInfo recordInfo = parkingRecordInfoService.selectParkingRecordInfoByPlate(plate);
        Integer pay = recordInfo.getPay();
        String payTime = recordInfo.getPayTime();


        String message = "plate&" + plate;
        System.out.println(message);
        return message;
    }


}
