package com.qw.providerblog.entity;

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
    private long total;
    private int pageSize;
    private List<T> rows;

    public PageResult(long total, int pageSize,List<T> rows) {
        this.total = total;
        this.pageSize=pageSize;
        this.rows = rows;
    }
}

