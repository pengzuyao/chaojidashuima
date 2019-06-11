package com.pzy.study.base.commons.pageHelper;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-09
 */
public class PageQuery {

    @Getter
    @Setter
    @Min(value = 1, message = "当前页码不合法")
    private int pageNo = 1;

    @Getter
    @Setter
    @Min(value = 1, message = "每页展示数量不合法")
    private int pageSize = 10;
}
