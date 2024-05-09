package com.ruoyi.parkingHistory.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.parkingHistory.domain.ParkingRecordInfo;
import com.ruoyi.parkingHistory.mapper.ParkingRecordInfoMapper;
import com.ruoyi.parkingHistory.service.IParkingRecordInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 停车记录Service业务层处理
 *
 * @author bigcar
 * @date 2024-05-08
 */
@Service
public class ParkingRecordInfoServiceImpl implements IParkingRecordInfoService {
    @Autowired
    private ParkingRecordInfoMapper parkingRecordInfoMapper;

    /**
     * 查询停车记录
     *
     * @param id 停车记录主键
     * @return 停车记录
     */
    @Override
    public ParkingRecordInfo selectParkingRecordInfoById(Long id) {
        return parkingRecordInfoMapper.selectParkingRecordInfoById(id);
    }

    /**
     * 查询停车记录列表
     *
     * @param parkingRecordInfo 停车记录
     * @return 停车记录
     */
    @Override
    public List<ParkingRecordInfo> selectParkingRecordInfoList(ParkingRecordInfo parkingRecordInfo) {
        List<ParkingRecordInfo> parkingRecordInfos = parkingRecordInfoMapper.selectParkingRecordInfoList(parkingRecordInfo);

//        Iterator iterator = parkingRecordInfos.iterator();
//        while (iterator.hasNext()) {
//            ParkingRecordInfo next = (ParkingRecordInfo)iterator.next();
//            if ("0".equals(next.getPayStatus()) && next.getPay() >= 0) {
//                // 注意！！！这里时Iterator.remove()!!!而不是list.remove()!!!
//                iterator.remove();
//            }
//        }
        return parkingRecordInfos;
    }

    /**
     * 新增停车记录
     *
     * @param parkingRecordInfo 停车记录
     * @return 结果
     */
    @Override
    public int insertParkingRecordInfo(ParkingRecordInfo parkingRecordInfo) {
        return parkingRecordInfoMapper.insertParkingRecordInfo(parkingRecordInfo);
    }

    /**
     * 修改停车记录
     *
     * @param parkingRecordInfo 停车记录
     * @return 结果
     */
    @Override
    public int updateParkingRecordInfo(ParkingRecordInfo parkingRecordInfo) {
        return parkingRecordInfoMapper.updateParkingRecordInfo(parkingRecordInfo);
    }

    /**
     * 批量删除停车记录
     *
     * @param ids 需要删除的停车记录主键
     * @return 结果
     */
    @Override
    public int deleteParkingRecordInfoByIds(String ids) {
        return parkingRecordInfoMapper.deleteParkingRecordInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除停车记录信息
     *
     * @param id 停车记录主键
     * @return 结果
     */
    @Override
    public int deleteParkingRecordInfoById(Long id) {
        return parkingRecordInfoMapper.deleteParkingRecordInfoById(id);
    }

    /**
     * 根据车牌号查询停车记录
     *
     * @param plate 停车记录主键
     * @return 停车记录
     */
    public ParkingRecordInfo selectParkingRecordInfoByPlate(String plate) {
        return parkingRecordInfoMapper.selectParkingRecordInfoByPlate(plate);
    }

    /**
     * 根据车牌号，支付金额，支付状态查询停车记录，车辆出库的时候用
     *
     * @param plate
     * @param pay
     * @param status
     * @return
     */
    public ParkingRecordInfo selectParkingRecordInfoByPlateAndStatus(String plate, int pay, int status) {
        return parkingRecordInfoMapper.selectParkingRecordInfoByPlateAndStatus(plate, pay, status);
    }
}
