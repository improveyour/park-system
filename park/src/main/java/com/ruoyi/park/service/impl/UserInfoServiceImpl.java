package com.ruoyi.park.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.park.domain.UserInfo;
import com.ruoyi.park.mapper.UserInfoMapper;
import com.ruoyi.park.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 查询用户Service业务层处理
 *
 * @author bigcar
 * @date 2024-04-29
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 查询查询用户
     *
     * @param id 查询用户主键
     * @return 查询用户
     */
    @Override
    public UserInfo selectUserInfoById(Long id) {
        return userInfoMapper.selectUserInfoById(id);
    }

    /**
     * 查询查询用户列表
     *
     * @param userInfo 查询用户
     * @return 查询用户
     */
    @Override
    public List<UserInfo> selectUserInfoList(UserInfo userInfo) {
        return userInfoMapper.selectUserInfoList(userInfo);
    }

    /**
     * 新增查询用户
     *
     * @param userInfo 查询用户
     * @return 结果
     */
    @Override
    public int insertUserInfo(UserInfo userInfo) {
        return userInfoMapper.insertUserInfo(userInfo);
    }

    /**
     * 修改查询用户
     *
     * @param userInfo 查询用户
     * @return 结果
     */
    @Override
    public int updateUserInfo(UserInfo userInfo) {
        return userInfoMapper.updateUserInfo(userInfo);
    }

    /**
     * 批量删除查询用户
     *
     * @param ids 需要删除的查询用户主键
     * @return 结果
     */
    @Override
    public int deleteUserInfoByIds(String ids) {
        return userInfoMapper.deleteUserInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除查询用户信息
     *
     * @param id 查询用户主键
     * @return 结果
     */
    @Override
    public int deleteUserInfoById(Long id) {
        return userInfoMapper.deleteUserInfoById(id);
    }

    /**
     * 根据电话号码查询用户信息
     *
     * @param phone
     * @return
     */
    @Override
    public UserInfo selectUserInfoByPhone(String phone) {
        return userInfoMapper.selectUserInfoByPhone(phone);
    }
}
