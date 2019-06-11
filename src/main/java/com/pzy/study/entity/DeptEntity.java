package com.pzy.study.entity;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Destription:部门表
 * Author: pengzuyao
 * Time: 2019-05-21
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeptEntity implements Serializable {

    private static final long serialVersionUID = -6422797252112215849L;

    private Integer id;  //部门id

    private String name;  //部门名称

    private Integer parentId;  //上级部门id

    private String level;  //部门层级

    private Integer seq; //部门在当前层级下的顺序，由小到大

    private String remark;

    private String operator;

    private Date operatorTime;

    private String operatorIp;

    private List<DeptEntity> deptList = Lists.newArrayList();
}
