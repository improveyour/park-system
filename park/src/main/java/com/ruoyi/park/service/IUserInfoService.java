package com.ruoyi.park.service;

import com.ruoyi.park.domain.UserInfo;

import java.util.List;

/**
 * 查询用户Service接口
 *
 * @author bigcar
 * @date 2024-04-29
 */
public interface IUserInfoService {
    /**
     * 查询查询用户
     *
     * @param id 查询用户主键
     * @return 查询用户
     */
    public UserInfo selectUserInfoById(Long id);

    /**
     * 查询查询用户列表
     *
     * @param userInfo 查询用户
     * @return 查询用户集合
     */
    public List<UserInfo> selectUserInfoList(UserInfo userInfo);

    /**
     * 新增查询用户
     *
     * @param userInfo 查询用户
     * @return 结果
     */
    public int insertUserInfo(UserInfo userInfo);

    /**
     * 修改查询用户
     *
     * @param userInfo 查询用户
     * @return 结果
     */
    public int updateUserInfo(UserInfo userInfo);

    /**
     * 批量删除查询用户
     *
     * @param ids 需要删除的查询用户主键集合
     * @return 结果
     */
    public int deleteUserInfoByIds(String ids);

    /**
     * 删除查询用户信息
     *
     * @param id 查询用户主键
     * @return 结果
     */
    public int deleteUserInfoById(Long id);

    /**
     * 根据电话号码查询用户信息
     *
     * @param phone
     * @return
     */
    public UserInfo selectUserInfoByPhone(String phone);
}
