package com.qw.consumerexternal.result;

import lombok.Data;

import java.util.List;

/**
 * @author Eclair
 */
@Data
public class PageResult <T>{
    private Long total;
    private List<T> rows;

    public PageResult(Long total,List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
