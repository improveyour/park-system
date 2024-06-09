package com.ruoyi.parkingInfo.service;

import com.ruoyi.parkingInfo.domain.CarPlateInfo;

import java.util.List;

/**
 * 车牌Service接口
 *
 * @author bigcar
 * @date 2024-05-01
 */
public interface ICarPlateInfoService {
    /**
     * 查询车牌
     *
     * @param id 车牌主键
     * @return 车牌
     */
    public CarPlateInfo selectCarPlateInfoById(Long id);

    /**
     * 通过手机号查询车牌
     *
     * @return 车牌列表
     */
    public List<CarPlateInfo> selectCarPlateInfoByPhone(String phone);

    /**
     * 查询车牌列表
     *
     * @param carPlateInfo 车牌
     * @return 车牌集合
     */
    public List<CarPlateInfo> selectCarPlateInfoList(CarPlateInfo carPlateInfo);

    /**
     * 新增车牌
     *
     * @param carPlateInfo 车牌
     * @return 结果
     */
    public int insertCarPlateInfo(CarPlateInfo carPlateInfo);

    /**
     * 修改车牌
     *
     * @param carPlateInfo 车牌
     * @return 结果
     */
    public int updateCarPlateInfo(CarPlateInfo carPlateInfo);

    /**
     * 批量删除车牌
     *
     * @param ids 需要删除的车牌主键集合
     * @return 结果
     */
    public int deleteCarPlateInfoByIds(String ids);

    /**
     * 删除车牌信息
     *
     * @param id 车牌主键
     * @return 结果
     */
    public int deleteCarPlateInfoById(Long id);
}
