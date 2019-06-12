package com.pzy.study.entity;

/*
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/06/12
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleAclRelEntity {

    private Integer id;

    private Integer roleId;

    private Integer aclId;

    private String operator;

    private Date operatorTime;

    private String operatorIp;
}
