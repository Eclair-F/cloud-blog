package com.qw.provideruserinformation.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @Program: cloud-blog
 * @ClassName User
 * @Description:
 * @Author: Eclair
 * @Create: 2019-12-31 15:43
 * @Version 1.0
 **/

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class Userinformation {
    private String id;
    private String userId;
    private List<Collect> collect;
    private List<Praise> praise;
}
