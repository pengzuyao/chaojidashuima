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
 * Time: 2019-06-09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogVo implements Serializable {

    private static final long serialVersionUID = -6317646717020142994L;

    private String id;  //权限模块id

    private String type; //权限模块名

    private String targetId;  //基于type后指定的对象id,比如用户、权限、角色等表的主键

    private String oldValue;  //旧值

    private String newValue;  //新值

    private String operator;

    private Date operatorTime;

    private String operatorIp;

    private String status; //当前是否复原过，0：没有，1：复原过
}
