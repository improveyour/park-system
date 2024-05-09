package com.ruoyi.parkingInfo.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.parkingInfo.domain.ParkingInfo;
import com.ruoyi.parkingInfo.mapper.ParkingInfoMapper;
import com.ruoyi.parkingInfo.service.IParkingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * 车位信息Service业务层处理
 *
 * @author bigcar
 * @date 2024-04-29
 */
@Service
public class ParkingInfoServiceImpl implements IParkingInfoService {
    @Autowired
    private ParkingInfoMapper parkingInfoMapper;

    // 停车位总数目
    private static Integer MAX_PARKING_SPACES = 20;

    // 用于标识车位是否被占用
    // 这种写法并不合理，按道理应该要查数据库
    private int flag[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    /**
     * 查询车位信息
     *
     * @param id 车位信息主键
     * @return 车位信息
     */
    @Override
    public ParkingInfo selectParkingInfoById(Long id) {
        return parkingInfoMapper.selectParkingInfoById(id);
    }

    /**
     * 查询车位信息列表
     *
     * @param parkingInfo 车位信息
     * @return 车位信息
     */
    @Override
    public List<ParkingInfo> selectParkingInfoList(ParkingInfo parkingInfo) {
        return parkingInfoMapper.selectParkingInfoList(parkingInfo);
    }

    /**
     * 新增车位信息
     *
     * @param parkingInfo 车位信息
     * @return 结果
     */
    @Override
    public int insertParkingInfo(ParkingInfo parkingInfo) {
        return parkingInfoMapper.insertParkingInfo(parkingInfo);
    }

    /**
     * 修改车位信息
     *
     * @param parkingInfo 车位信息
     * @return 结果
     */
    @Override
    public int updateParkingInfo(ParkingInfo parkingInfo) {
        return parkingInfoMapper.updateParkingInfo(parkingInfo);
    }

    /**
     * 批量删除车位信息
     *
     * @param ids 需要删除的车位信息主键
     * @return 结果
     */
    @Override
    public int deleteParkingInfoByIds(String ids) {
        return parkingInfoMapper.deleteParkingInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除车位信息信息
     *
     * @param id 车位信息主键
     * @return 结果
     */
    @Override
    public int deleteParkingInfoById(Long id) {
        return parkingInfoMapper.deleteParkingInfoById(id);
    }

    /**
     * 车辆入库，随机存放到一个空闲的停车位
     *
     * @param plate 车牌信息
     * @return 停车位编号
     */
    @Override
    public Long carPark(String plate) {
        Random rand = new Random();
        int MAX = 14019, MIN = 14000;
        int id;
        int count = 20;
        for (int i = 0; i < MAX_PARKING_SPACES; i++) {
            if (flag[i] == 1) {
                count--;
            }
        }
        System.out.println("目前有==========> " + count + " 个空闲车位");
        // 在进行入库操作前，需要判断停车场是否有空闲车位
        if (count < MAX_PARKING_SPACES) {
            while (true) {
                id = rand.nextInt(MAX - MIN + 1) + MIN;
                // 表示车位为空
                if (flag[id - 14000] == 0) {
                    System.out.println("在第 " + flag[id - 14000] + " 车位");
                    flag[id - 14000] = 1;
                    break;
                }
            }
            // 此时的 id 即为车位编号
            Long idl = new Long(id);

            ParkingInfo parkingInfo = new ParkingInfo();
            parkingInfo.setId(idl);
            parkingInfo.setStatus(1);
            parkingInfo.setCarPlate(plate);

            //更新车位信息表
            int i = parkingInfoMapper.updateParkingInfo(parkingInfo);
            System.out.println("受影响的行数=====>" + i);
            ParkingInfo info = parkingInfoMapper.selectParkingInfoById(idl);
            System.out.println(info.toString());

            return (long) id;
        }
        return null;
    }


    /**
     * 根据车牌号修改车位信息，用于出库操作
     *
     * @param plate
     * @return
     */
    @Override
    public ParkingInfo updateParkingInfoByPlate(String plate) {
        return parkingInfoMapper.updateParkingInfoByPlate(plate);
    }
}
