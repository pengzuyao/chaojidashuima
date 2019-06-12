package com.pzy.study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Destription: 角色用户关系表
 * Author: pengzuyao
 * Time: 2019-05-21
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleUserRelEntity implements Serializable {


    private static final long serialVersionUID = 3660456812777412117L;

    private Integer id;  //角色用户id

    private Integer roleId;  //角色id

    private Integer userId;  //用户id

    private String operator;

    private Date operatorTime;

    private String operatorIp;
}
