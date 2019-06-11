package com.pzy.study.service;

import com.pzy.study.entity.AclEntity;
import com.pzy.study.entity.AclModuleLevelEntity;
import com.pzy.study.entity.RoleAclRelEntity;
import com.pzy.study.entity.RoleEntity;
import com.pzy.study.vo.RoleVo;

import java.util.List;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-03
 */
public interface RoleService {

    void insertRole(RoleVo roleVo);

    void updateRole(RoleVo roleVo);

    List<RoleEntity> getAll();

    List<RoleAclRelEntity> getCurrentUserAclList();

    List<AclModuleLevelEntity> roleAclTree(Integer roleId);
}
