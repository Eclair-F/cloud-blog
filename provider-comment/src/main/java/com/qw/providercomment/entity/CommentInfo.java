package com.qw.providercomment.entity;

import lombok.Data;

/**
 * @Program: cloud-blog
 * @ClassName CommentInfo
 * @Description:
 * @Author: Eclair
 * @Create: 2020-02-27 22:53
 * @Version 1.0
 **/
@Data
public class CommentInfo {
    private String id;
    private String userId;
    private String replyId;
}
