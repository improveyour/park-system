package com.ruoyi.parkingInfo.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.parkingInfo.domain.ParkingInfo;
import com.ruoyi.parkingInfo.mapper.ParkingInfoMapper;
import com.ruoyi.parkingInfo.service.IParkingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
