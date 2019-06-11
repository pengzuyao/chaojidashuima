package com.pzy.study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-21
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AclModuleEntity implements Serializable {

    private static final long serialVersionUID = -5163756386048517080L;

    private Integer id;  //权限模块id

    private String name; //权限模块名

    private Integer parentId;  //父模块id

    private String level;  //部门层级

    private Integer status; //状态，1：正常，0：冻结

    private Integer seq; //权限在当前模块下的顺序，由小到大

    private String remark;

    private String operator;

    private Date operatorTime;

    private String operatorIp;

}
