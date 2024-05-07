package com.ruoyi.parkingHistory.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.parkingHistory.domain.ParkingRecordInfo;
import com.ruoyi.parkingHistory.mapper.ParkingRecordInfoMapper;
import com.ruoyi.parkingHistory.service.IParkingRecordInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 查询停车记录Service业务层处理
 *
 * @author bigcar
 * @date 2024-05-07
 */
@Service
public class ParkingRecordInfoServiceImpl implements IParkingRecordInfoService {
    @Autowired
    private ParkingRecordInfoMapper parkingRecordInfoMapper;

    /**
     * 查询查询停车记录
     *
     * @param id 查询停车记录主键
     * @return 查询停车记录
     */
    @Override
    public ParkingRecordInfo selectParkingRecordInfoById(Long id) {
        return parkingRecordInfoMapper.selectParkingRecordInfoById(id);
    }

    /**
     * 查询查询停车记录列表
     *
     * @param parkingRecordInfo 查询停车记录
     * @return 查询停车记录
     */
    @Override
    public List<ParkingRecordInfo> selectParkingRecordInfoList(ParkingRecordInfo parkingRecordInfo) {
        return parkingRecordInfoMapper.selectParkingRecordInfoList(parkingRecordInfo);
    }

    /**
     * 新增查询停车记录
     *
     * @param parkingRecordInfo 查询停车记录
     * @return 结果
     */
    @Override
    public int insertParkingRecordInfo(ParkingRecordInfo parkingRecordInfo) {
        return parkingRecordInfoMapper.insertParkingRecordInfo(parkingRecordInfo);
    }

    /**
     * 修改查询停车记录
     *
     * @param parkingRecordInfo 查询停车记录
     * @return 结果
     */
    @Override
    public int updateParkingRecordInfo(ParkingRecordInfo parkingRecordInfo) {
        return parkingRecordInfoMapper.updateParkingRecordInfo(parkingRecordInfo);
    }

    /**
     * 批量删除查询停车记录
     *
     * @param ids 需要删除的查询停车记录主键
     * @return 结果
     */
    @Override
    public int deleteParkingRecordInfoByIds(String ids) {
        return parkingRecordInfoMapper.deleteParkingRecordInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除查询停车记录信息
     *
     * @param id 查询停车记录主键
     * @return 结果
     */
    @Override
    public int deleteParkingRecordInfoById(Long id) {
        return parkingRecordInfoMapper.deleteParkingRecordInfoById(id);
    }
}
