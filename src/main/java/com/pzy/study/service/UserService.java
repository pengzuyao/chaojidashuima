package com.pzy.study.service;


import com.pzy.study.base.commons.utils.Result;
import com.pzy.study.entity.UserEntity;
import com.pzy.study.vo.UserVo;


/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-19
 */
public interface UserService {

    public UserEntity findUserInfoByName(String uname);

    public void save(UserVo userVO);

    public void update(UserVo userVO);

    Result userPageQuery(UserVo userVO);

    void changeRoleUsers(Integer roleId ,String userIds);
 }
