package com.qw.provideruserinformation.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Program: cloud-blog
 * @ClassName Praise
 * @Description:
 * @Author: Eclair
 * @Create: 2020-02-26 15:58
 * @Version 1.0
 **/

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class Praise {
    String blogId;
    String date;
}
