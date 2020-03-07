package com.qw.providercomment.entity;

import lombok.Data;

import java.util.List;

/**
 * @Program: cloud-blog
 * @ClassName PageResult
 * @Description:
 * @Author: Eclair
 * @Create: 2019-12-31 15:45
 * @Version 1.0
 **/
@Data
public class PageResult<T> {
    private Long total;
    private List<T> rows;

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
