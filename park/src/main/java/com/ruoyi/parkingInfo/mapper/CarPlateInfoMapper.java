package com.ruoyi.parkingInfo.mapper;

import com.ruoyi.parkingInfo.domain.CarPlateInfo;

import java.util.List;

/**
 * 车牌Mapper接口
 *
 * @author bigcar
 * @date 2024-05-01
 */
public interface CarPlateInfoMapper {
    /**
     * 查询车牌
     *
     * @param id 车牌主键
     * @return 车牌
     */
    public CarPlateInfo selectCarPlateInfoById(Long id);

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
     * 删除车牌
     *
     * @param id 车牌主键
     * @return 结果
     */
    public int deleteCarPlateInfoById(Long id);

    /**
     * 批量删除车牌
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCarPlateInfoByIds(String[] ids);
}
