package com.qw.providerblog.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Program: cloud-blog
 * @ClassName Blog
 * @Description:
 * @Author: Eclair
 * @Create: 2020-01-14 15:20
 * @Version 1.0
 **/

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class Blog {
    private String id;
    private String title;
    private String cover;
    private String content;
    private String userId;
    private String category;
    private String date;
    private String draft;
    private Integer commentNum;
    private Integer praise;
    private Integer view;
}
