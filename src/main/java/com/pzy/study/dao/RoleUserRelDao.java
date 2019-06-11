package com.pzy.study.dao;

import com.pzy.study.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-19
 */
@Mapper
public interface RoleUserRelDao {

    List<Integer> selectRoleIdsByUserId(Integer userId);

    List<Integer> selectUserIdsByRoleId(Integer roleId);

}
