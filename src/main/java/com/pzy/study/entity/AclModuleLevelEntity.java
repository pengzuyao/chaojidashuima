package com.pzy.study.entity;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AclModuleLevelEntity extends AclModuleEntity {

    private static final long serialVersionUID = 4933137124075776638L;

    private List<AclModuleLevelEntity> aclModuleList = Lists.newArrayList();

    private List<RoleAclEntity> aclList = Lists.newArrayList();

    public static AclModuleLevelEntity adapt(AclModuleEntity aclModuleEntity){
        AclModuleLevelEntity aclModuleLevelEntity = new AclModuleLevelEntity();
        BeanUtils.copyProperties(aclModuleEntity , aclModuleLevelEntity);
        return aclModuleLevelEntity;
    }
}
