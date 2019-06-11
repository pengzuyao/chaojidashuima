package com.pzy.study.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeptVo implements Serializable {

    private static final long serialVersionUID = 775782998488243079L;

    private Integer id;

    @NotBlank(message = "部门名称名称不能为空")
    private String name;

    private Integer parentId;

    private Integer seq;

    private String remark;
}
