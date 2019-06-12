package com.pzy.study.service;

import com.pzy.study.entity.*;
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

    List<RoleAclEntity> getCurrentUserAclList();

    List<AclModuleLevelEntity> roleAclTree(Integer roleId);
}
