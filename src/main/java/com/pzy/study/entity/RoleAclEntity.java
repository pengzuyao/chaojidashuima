package com.pzy.study.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-21
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleAclEntity extends AclEntity{

    private static final long serialVersionUID = 1894342793919308927L;

    /**
     * 是否要默认选中
     */
    private  boolean checked  = false;

    /**
     * 是否有权限操作
     */
    private boolean hasAcl = false;


    public static RoleAclEntity adapt(AclEntity aclEntity){
        RoleAclEntity roleAclEntity = new RoleAclEntity();
        BeanUtils.copyProperties(aclEntity , roleAclEntity);
        return roleAclEntity;
    }
}
