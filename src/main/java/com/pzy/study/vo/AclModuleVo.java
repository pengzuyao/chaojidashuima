package com.pzy.study.vo;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AclModuleVo implements Serializable {

    private static final long serialVersionUID = -274286887557019339L;

    private Integer id;

    private String name;

    private Integer parentId;

    private Integer seq;

    private Integer status;

    private String remark;

}
