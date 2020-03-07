package com.qw.providercomment.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author Eclair
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Comment {
    private String id;
    private String blogId;
    private String content;
    private String userId;
    private String userNickname;
    private String date;
    private List<Reply> replies;
}
