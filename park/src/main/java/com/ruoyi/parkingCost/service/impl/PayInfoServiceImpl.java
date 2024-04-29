package com.ruoyi.parkingCost.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.parkingCost.domain.PayInfo;
import com.ruoyi.parkingCost.mapper.PayInfoMapper;
import com.ruoyi.parkingCost.service.IPayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收费标准Service业务层处理
 *
 * @author bigcar
 * @date 2024-04-29
 */
@Service
public class PayInfoServiceImpl implements IPayInfoService {
    @Autowired
    private PayInfoMapper payInfoMapper;

    /**
     * 查询收费标准
     *
     * @param id 收费标准主键
     * @return 收费标准
     */
    @Override
    public PayInfo selectPayInfoById(Long id) {
        return payInfoMapper.selectPayInfoById(id);
    }

    /**
     * 查询收费标准列表
     *
     * @param payInfo 收费标准
     * @return 收费标准
     */
    @Override
    public List<PayInfo> selectPayInfoList(PayInfo payInfo) {
        return payInfoMapper.selectPayInfoList(payInfo);
    }

    /**
     * 新增收费标准
     *
     * @param payInfo 收费标准
     * @return 结果
     */
    @Override
    public int insertPayInfo(PayInfo payInfo) {
        return payInfoMapper.insertPayInfo(payInfo);
    }

    /**
     * 修改收费标准
     *
     * @param payInfo 收费标准
     * @return 结果
     */
    @Override
    public int updatePayInfo(PayInfo payInfo) {
        return payInfoMapper.updatePayInfo(payInfo);
    }

    /**
     * 批量删除收费标准
     *
     * @param ids 需要删除的收费标准主键
     * @return 结果
     */
    @Override
    public int deletePayInfoByIds(String ids) {
        return payInfoMapper.deletePayInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除收费标准信息
     *
     * @param id 收费标准主键
     * @return 结果
     */
    @Override
    public int deletePayInfoById(Long id) {
        return payInfoMapper.deletePayInfoById(id);
    }
}
