package com.ruoyi.parkingHistory.mapper;

import com.ruoyi.parkingHistory.domain.ParkingRecordInfo;

import java.util.List;

/**
 * 查询停车记录Mapper接口
 *
 * @author bigcar
 * @date 2024-04-29
 */
public interface ParkingRecordInfoMapper {
    /**
     * 查询查询停车记录
     *
     * @param id 查询停车记录主键
     * @return 查询停车记录
     */
    public ParkingRecordInfo selectParkingRecordInfoById(Long id);

    /**
     * 查询查询停车记录列表
     *
     * @param parkingRecordInfo 查询停车记录
     * @return 查询停车记录集合
     */
    public List<ParkingRecordInfo> selectParkingRecordInfoList(ParkingRecordInfo parkingRecordInfo);

    /**
     * 新增查询停车记录
     *
     * @param parkingRecordInfo 查询停车记录
     * @return 结果
     */
    public int insertParkingRecordInfo(ParkingRecordInfo parkingRecordInfo);

    /**
     * 修改查询停车记录
     *
     * @param parkingRecordInfo 查询停车记录
     * @return 结果
     */
    public int updateParkingRecordInfo(ParkingRecordInfo parkingRecordInfo);

    /**
     * 删除查询停车记录
     *
     * @param id 查询停车记录主键
     * @return 结果
     */
    public int deleteParkingRecordInfoById(Long id);

    /**
     * 批量删除查询停车记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteParkingRecordInfoByIds(String[] ids);
}
