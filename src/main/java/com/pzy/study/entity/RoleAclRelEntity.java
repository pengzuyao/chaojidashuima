package com.pzy.study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.security.acl.Acl;
import java.util.Date;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-21
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleAclRelEntity extends AclEntity{

    private static final long serialVersionUID = 1894342793919308927L;

    /**
     * 是否要默认选中
     */
    private  boolean checked  = false;

    /**
     * 是否有权限操作
     */
    private boolean hasAcl = false;


    public static RoleAclRelEntity adapt(AclEntity aclEntity){
        RoleAclRelEntity roleAclRelEntity = new RoleAclRelEntity();
        BeanUtils.copyProperties(aclEntity , roleAclRelEntity);
        return roleAclRelEntity;
    }
}
