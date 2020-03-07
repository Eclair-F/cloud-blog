package com.qw.providercomment.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Program: cloud-blog
 * @ClassName Reply
 * @Description:
 * @Author: Eclair
 * @Create: 2020-01-19 18:19
 * @Version 1.0
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Reply {
    String id;
    String userId;
    String userNickname;
    String content;
    String date;
}
