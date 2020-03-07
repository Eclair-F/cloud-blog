package com.qw.provideruserinformation.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Program: cloud-blog
 * @ClassName Collect
 * @Description:
 * @Author: Eclair
 * @Create: 2020-02-26 15:56
 * @Version 1.0
 **/

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class Collect {
    String blogId;
    String date;

}
