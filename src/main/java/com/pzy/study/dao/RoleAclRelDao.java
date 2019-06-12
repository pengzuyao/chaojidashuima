package com.pzy.study.dao;


import com.pzy.study.entity.RoleAclRelEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-19
 */
@Mapper
public interface RoleAclRelDao {

   void batchRoleAclInsert(@Param("roleAclList") List<RoleAclRelEntity> roleAclList);
}
