package com.ruoyi.parkingHistory.service;

import com.ruoyi.parkingHistory.domain.ParkingRecordInfo;

import java.util.List;

/**
 * 停车记录Service接口
 *
 * @author bigcar
 * @date 2024-05-08
 */
public interface IParkingRecordInfoService {
    /**
     * 查询停车记录
     *
     * @param id 停车记录主键
     * @return 停车记录
     */
    public ParkingRecordInfo selectParkingRecordInfoById(Long id);

    /**
     * 查询停车记录列表
     *
     * @param parkingRecordInfo 停车记录
     * @return 停车记录集合
     */
    public List<ParkingRecordInfo> selectParkingRecordInfoList(ParkingRecordInfo parkingRecordInfo);

    /**
     * 新增停车记录
     *
     * @param parkingRecordInfo 停车记录
     * @return 结果
     */
    public int insertParkingRecordInfo(ParkingRecordInfo parkingRecordInfo);

    /**
     * 修改停车记录
     *
     * @param parkingRecordInfo 停车记录
     * @return 结果
     */
    public int updateParkingRecordInfo(ParkingRecordInfo parkingRecordInfo);

    /**
     * 批量删除停车记录
     *
     * @param ids 需要删除的停车记录主键集合
     * @return 结果
     */
    public int deleteParkingRecordInfoByIds(String ids);

    /**
     * 删除停车记录信息
     *
     * @param id 停车记录主键
     * @return 结果
     */
    public int deleteParkingRecordInfoById(Long id);

    /**
     * 根据车牌查询停车记录，车辆出库的时候用
     *
     * @param plate 停车记录主键
     * @return 停车记录
     */
    public ParkingRecordInfo selectParkingRecordInfoByPlate(String plate);

    /**
     * 根据车牌号，支付金额，支付状态查询停车记录，车辆出库的时候用
     *
     * @param plate
     * @param pay
     * @param status
     * @return
     */
    public ParkingRecordInfo selectParkingRecordInfoByPlateAndStatus(String plate, int pay, int status);
}
