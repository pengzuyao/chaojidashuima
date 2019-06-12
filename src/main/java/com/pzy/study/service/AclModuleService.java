package com.pzy.study.service;

import com.pzy.study.entity.AclModuleEntity;
import com.pzy.study.entity.AclModuleLevelEntity;
import com.pzy.study.vo.AclModuleVo;

import java.util.List;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-26
 */
public interface AclModuleService {

    public  void saveAclModule(AclModuleVo aclModuleVO);

    public List<AclModuleLevelEntity> getAclModuleTree();

    public void updateaclModule(AclModuleVo aclModuleVO);
}
