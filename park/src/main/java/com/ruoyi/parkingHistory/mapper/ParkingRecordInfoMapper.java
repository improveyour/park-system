package com.ruoyi.parkingHistory.mapper;

import com.ruoyi.parkingHistory.domain.ParkingRecordInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 停车记录Mapper接口
 *
 * @author bigcar
 * @date 2024-05-08
 */
public interface ParkingRecordInfoMapper {
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
     * 删除停车记录
     *
     * @param id 停车记录主键
     * @return 结果
     */
    public int deleteParkingRecordInfoById(Long id);

    /**
     * 批量删除停车记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteParkingRecordInfoByIds(String[] ids);

    /**
     * 根据车牌号查询停车记录
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
    public ParkingRecordInfo selectParkingRecordInfoByPlateAndStatus(@Param("plate") String plate, @Param("pay") int pay, @Param("status") int status);
}
