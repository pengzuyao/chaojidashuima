package com.pzy.study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Destription:角色表
 * Author: pengzuyao
 * Time: 2019-05-19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity  implements Serializable {

    private static final long serialVersionUID = 956774764110627123L;

    private Integer id;   //角色id

    private String name;  //角色名

    private Integer type;  //角色类型，1:管理员角色，2：其它

    private Integer status; //状态，1：正常，0：冻结

    private String remark;

    private String operator;

    private Date operatorTime;

    private String operatorIp;






}
