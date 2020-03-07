package com.qw.providerblog.entity;

import lombok.Data;

/**
 * @Program: cloud-blog
 * @ClassName Blog
 * @Description:
 * @Author: Eclair
 * @Create: 2020-01-14 15:20
 * @Version 1.0
 **/
@Data
public class Blog {
    private String id;
    private String title;
    private String content;
    private String userId;
    private String category;
    private String date;
    private Integer collect;
    private Integer praise;
    private Integer view;
}
