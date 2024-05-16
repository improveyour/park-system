package com.ruoyi.parkingCost.service;

import com.ruoyi.parkingCost.domain.PayInfo;

import java.util.List;

/**
 * 收费标准Service接口
 *
 * @author bigcar
 * @date 2024-05-13
 */
public interface IPayInfoService {
    /**
     * 查询收费标准
     *
     * @param id 收费标准主键
     * @return 收费标准
     */
    public PayInfo selectPayInfoById(Long id);

    /**
     * 查询收费标准列表
     *
     * @param payInfo 收费标准
     * @return 收费标准集合
     */
    public List<PayInfo> selectPayInfoList(PayInfo payInfo);

    /**
     * 新增收费标准
     *
     * @param payInfo 收费标准
     * @return 结果
     */
    public int insertPayInfo(PayInfo payInfo);

    /**
     * 修改收费标准
     *
     * @param payInfo 收费标准
     * @return 结果
     */
    public int updatePayInfo(PayInfo payInfo);

    /**
     * 批量删除收费标准
     *
     * @param ids 需要删除的收费标准主键集合
     * @return 结果
     */
    public int deletePayInfoByIds(String ids);

    /**
     * 删除收费标准信息
     *
     * @param id 收费标准主键
     * @return 结果
     */
    public int deletePayInfoById(Long id);

    /**
     * 获取全部收费标准
     *
     * @return
     */
    public List<PayInfo> getAll();
}
