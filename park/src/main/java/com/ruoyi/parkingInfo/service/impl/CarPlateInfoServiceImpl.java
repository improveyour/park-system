package com.ruoyi.parkingInfo.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.parkingInfo.domain.CarPlateInfo;
import com.ruoyi.parkingInfo.mapper.CarPlateInfoMapper;
import com.ruoyi.parkingInfo.service.ICarPlateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车牌Service业务层处理
 *
 * @author bigcar
 * @date 2024-05-01
 */
@Service
public class CarPlateInfoServiceImpl implements ICarPlateInfoService {
    @Autowired
    private CarPlateInfoMapper carPlateInfoMapper;

    /**
     * 查询车牌
     *
     * @param id 车牌主键
     * @return 车牌
     */
    @Override
    public CarPlateInfo selectCarPlateInfoById(Long id) {
        return carPlateInfoMapper.selectCarPlateInfoById(id);
    }

    /**
     * 查询车牌列表
     *
     * @param carPlateInfo 车牌
     * @return 车牌
     */
    @Override
    public List<CarPlateInfo> selectCarPlateInfoList(CarPlateInfo carPlateInfo) {
        return carPlateInfoMapper.selectCarPlateInfoList(carPlateInfo);
    }

    /**
     * 新增车牌
     *
     * @param carPlateInfo 车牌
     * @return 结果
     */
    @Override
    public int insertCarPlateInfo(CarPlateInfo carPlateInfo) {
        return carPlateInfoMapper.insertCarPlateInfo(carPlateInfo);
    }

    /**
     * 修改车牌
     *
     * @param carPlateInfo 车牌
     * @return 结果
     */
    @Override
    public int updateCarPlateInfo(CarPlateInfo carPlateInfo) {
        return carPlateInfoMapper.updateCarPlateInfo(carPlateInfo);
    }

    /**
     * 批量删除车牌
     *
     * @param ids 需要删除的车牌主键
     * @return 结果
     */
    @Override
    public int deleteCarPlateInfoByIds(String ids) {
        return carPlateInfoMapper.deleteCarPlateInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除车牌信息
     *
     * @param id 车牌主键
     * @return 结果
     */
    @Override
    public int deleteCarPlateInfoById(Long id) {
        return carPlateInfoMapper.deleteCarPlateInfoById(id);
    }

    /**
     * 通过手机号查询车牌
     *
     * @return 车牌列表
     */
    public List<CarPlateInfo> selectCarPlateInfoByPhone(String phone) {
        return carPlateInfoMapper.selectCarPlateInfoByPhone(phone);
    }
}
