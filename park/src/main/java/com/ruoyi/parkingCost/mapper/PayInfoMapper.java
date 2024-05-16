package com.ruoyi.parkingCost.mapper;

import com.ruoyi.parkingCost.domain.PayInfo;

import java.util.List;

/**
 * 收费标准Mapper接口
 *
 * @author bigcar
 * @date 2024-05-13
 */
public interface PayInfoMapper {
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
     * 删除收费标准
     *
     * @param id 收费标准主键
     * @return 结果
     */
    public int deletePayInfoById(Long id);

    /**
     * 批量删除收费标准
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePayInfoByIds(String[] ids);

    /**
     * 获取全部收费标准
     *
     * @return
     */
    public List<PayInfo> getAll();
}
