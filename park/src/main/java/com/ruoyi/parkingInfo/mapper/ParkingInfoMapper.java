package com.ruoyi.parkingInfo.mapper;

import com.ruoyi.parkingInfo.domain.ParkingInfo;

import java.util.List;

/**
 * 车位信息Mapper接口
 *
 * @author bigcar
 * @date 2024-04-29
 */
public interface ParkingInfoMapper {
    /**
     * 查询车位信息
     *
     * @param id 车位信息主键
     * @return 车位信息
     */
    public ParkingInfo selectParkingInfoById(Long id);

    /**
     * 查询车位信息列表
     *
     * @param parkingInfo 车位信息
     * @return 车位信息集合
     */
    public List<ParkingInfo> selectParkingInfoList(ParkingInfo parkingInfo);

    /**
     * 新增车位信息
     *
     * @param parkingInfo 车位信息
     * @return 结果
     */
    public int insertParkingInfo(ParkingInfo parkingInfo);

    /**
     * 修改车位信息
     *
     * @param parkingInfo 车位信息
     * @return 结果
     */
    public int updateParkingInfo(ParkingInfo parkingInfo);

    /**
     * 删除车位信息
     *
     * @param id 车位信息主键
     * @return 结果
     */
    public int deleteParkingInfoById(Long id);

    /**
     * 批量删除车位信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteParkingInfoByIds(String[] ids);
}
