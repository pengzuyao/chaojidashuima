package com.pzy.study.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.pzy.study.base.commons.utils.IpAddressUtil;
import com.pzy.study.base.commons.utils.PasswordHelper;
import com.pzy.study.base.commons.utils.RequestHolder;
import com.pzy.study.base.commons.utils.Result;
import com.pzy.study.dao.RoleUserRelDao;
import com.pzy.study.dao.UserDao;
import com.pzy.study.entity.UserEntity;
import com.pzy.study.service.UserService;
import com.pzy.study.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-19
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDAO;

    @Autowired
    private RoleUserRelDao roleUserRelDao;

    @Override
    public UserEntity findUserInfoByName(String uname) {
        return userDAO.findUserInfoByName(uname);
    }

    @Override
    public Result userPageQuery(UserVo userVO) {
        PageHelper.startPage(userVO.getPageNo() , userVO.getPageSize());
        List<UserEntity> userEntities = Lists.newArrayList();
        userEntities = userDAO.selectUsersByDeptId(userVO.getDeptId());
        return Result.success().set(Result.DATA_KEY ,new PageInfo<UserEntity>(userEntities));
    }

    @Override
    public void save(UserVo userVO) {
        UserEntity userEntity = UserEntity.builder().
                username(userVO.getUsername()).
                password(StringUtils.isBlank(userVO.getPassword()) ? "admin" : userVO.getPassword()).
                phoneNo(userVO.getPhoneNo()).
                mail(userVO.getMail()).
                deptId(userVO.getDeptId()).
                remark(userVO.getRemark()).
                operator("admin").
                operatorIp("127.0.0.1").
                build();
                //加密
                PasswordHelper.encryptPassword(userEntity);
                userDAO.addUser(userEntity);
    }

    @Override
    public void update(UserVo userVO) {
        UserEntity userEntity = UserEntity.builder().
                id(userVO.getId()).
                username(userVO.getUsername()).
                phoneNo(userVO.getPhoneNo()).
                mail(userVO.getMail()).
                deptId(userVO.getDeptId()).
                operator(RequestHolder.getCurrentUser().getUsername()).
                operatorIp(IpAddressUtil.getIpAdrress(RequestHolder.getCurrentRequest())).
                build();
        userDAO.updateUser(userEntity);
    }

    @Override
    public Result findRoleUsers(Integer roleId) {
        List<UserEntity> allUsers = userDAO.selectAll();
        List<Integer> userIds = roleUserRelDao.selectUserIdsByRoleId(roleId);
        List<UserEntity> selectUsers = userDAO.selectByUserIds(userIds);
        List<Integer> selectUserIds = selectUsers.stream().map(userEntity -> userEntity.getId()).collect(Collectors.toList());
        List<UserEntity> unSelectUsers = allUsers.stream().filter(userEntity -> !selectUserIds.contains(userEntity.getId())).collect(Collectors.toList());
        return Result.success().set("select" ,selectUsers).set("unselect" ,unSelectUsers);
    }

}
