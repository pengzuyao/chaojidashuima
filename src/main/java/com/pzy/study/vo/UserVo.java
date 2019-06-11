package com.pzy.study.vo;

import com.pzy.study.base.commons.pageHelper.PageQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVo extends PageQuery implements Serializable {

    private static final long serialVersionUID = -7847795608835801007L;

    private Integer id ;  //用户id

    private String username; //用户名

    private String password;//密码

    private String phoneNo; //手机号

    private String mail; //邮箱

    private String remark;

    private Integer deptId;

    private String status;
}
