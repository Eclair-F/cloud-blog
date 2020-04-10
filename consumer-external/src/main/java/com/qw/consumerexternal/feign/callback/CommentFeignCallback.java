package com.qw.consumerexternal.feign.callback;

import com.qw.consumerexternal.feign.CommentFeign;
import org.springframework.stereotype.Component;

/**
 * @Program: cloud-blog
 * @ClassName CommentFeignCallback
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-25 14:59
 * @Version 1.0
 **/
@Component
public class CommentFeignCallback implements CommentFeign {
    @Override
    public Object comment() {
        return null;
    }

    @Override
    public Object findById(String blogId) {
        return null;
    }

    @Override
    public boolean createComment(Object comment) {
        return false;
    }

    @Override
    public Object updateReply(Object comment) {
        return null;
    }

    @Override
    public boolean deleteComment(Object commentInfo) {
        return false;
    }

    @Override
    public boolean deleteAll(String id) {
        return false;
    }

    @Override
    public boolean deleteReply(Object commentInfo) {
        return false;
    }

    @Override
    public Object message() {
        return null;
    }

}
