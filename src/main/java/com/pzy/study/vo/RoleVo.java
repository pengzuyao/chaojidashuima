package com.pzy.study.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-03
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleVo implements Serializable {

    private static final long serialVersionUID = -6678708889674075476L;

    private Integer id;   //角色id

    private String name;  //角色名

    private Integer type;  //角色类型，1:管理员角色，2：其它

    private Integer status; //状态，1：正常，0：冻结

    private String remark;

    private String operator;

    private Date operatorTime;

    private String operatorIp;
}
