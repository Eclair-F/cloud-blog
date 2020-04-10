package com.qw.providercomment.services.implement;

import com.qw.providercomment.dao.CommentDao;
import com.qw.providercomment.entity.Comment;
import com.qw.providercomment.entity.CommentInfo;
import com.qw.providercomment.entity.PageResult;
import com.qw.providercomment.entity.Reply;
import com.qw.providercomment.services.CommentService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Program: cloud-blog
 * @ClassName CommentServiceImpl
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-28 14:16
 * @Version 1.0
 **/
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
    private PageResult<Comment> commentPageResultTool(PageResult<Comment> pageResult) {
        if (pageResult.getRows().isEmpty()) {
            return null;
        } else {
            return pageResult;
        }
    }
    /**
     * 添加评论
     */
    @Override
    public boolean createComment(Comment comment) {
        comment.setInputShow(false);
        comment.setReplyShow(false);
        comment.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        return commentDao.createComment(comment) != null;
    }

    /**
     * 根据id删除评论
     */
    @Override
    public boolean deleteById(String id) {
        return commentDao.deleteById(id).getDeletedCount() != 0;
    }

    /**
     * 根据id删除评论
     */
    @Override
    public boolean deleteAll(String id) {
        return !commentDao.deleteAll(id).isEmpty();
    }

    /**
     * 根据id删除回复
     */
    @Override
    public boolean deleteById(CommentInfo commentInfo) {
        Reply reply = new Reply();
        reply.setId(commentInfo.getReplyId());
        return commentDao.deleteById(commentInfo.getId(), reply).getModifiedCount() != 0;
    }

    /**
     * 根据博客id查找评论
     */
    @Override
    public PageResult<Comment> findByBlogId(String blogId) {
        return commentPageResultTool(commentDao.findByBlogId(blogId));
    }

    /**
     * 查找全部留言
     **/
    @Override
    public PageResult<Comment> findAllMessage() {
        return  commentPageResultTool(commentDao.findMessage());
    }

    @Override
    public boolean updateReply(Comment comment) {
        Reply reply = comment.getReplies().get(0);
        reply.setId(new ObjectId().toHexString());
        reply.setInputShow(false);
        reply.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        return commentDao.updateReply(comment.getId(), reply).getModifiedCount() != 0;
    }
}
