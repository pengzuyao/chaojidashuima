package com.pzy.study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * Destription:用户表
 * Author: pengzuyao
 * Time: 2019-05-19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -432462382380133708L;

    private Integer id ;  //用户id

    private String username; //用户名

    private String password; //用户密码

    private String salt; //盐值

    private String phoneNo; //手机号

    private String mail; //邮箱

    private String remark;

    private Integer deptId;

    private String status;

    private String operator;

    private Date operatorTime;

    private String operatorIp;

}
