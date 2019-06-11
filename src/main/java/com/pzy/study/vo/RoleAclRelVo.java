package com.pzy.study.vo;

import lombok.*;

import java.io.Serializable;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-06
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class RoleAclRelVo extends AclVo{

    private static final long serialVersionUID = -2753773093642966943L;

    /**
     * 是否要默认选中
     */
    private  boolean checked  = false;

    /**
     * 是否有权限操作
     */
    private boolean hasAcl = false;

}
