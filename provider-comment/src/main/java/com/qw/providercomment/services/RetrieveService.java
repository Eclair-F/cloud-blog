package com.qw.providercomment.services;

import com.qw.providercomment.dao.RetrieveDao;
import com.qw.providercomment.entity.Comment;
import com.qw.providercomment.entity.PageResult;
import org.springframework.stereotype.Service;

/**
 * @author Eclair
 */
@Service
public class RetrieveService {
    private final RetrieveDao retrieveDao;

    public RetrieveService(RetrieveDao retrieveDao) {
        this.retrieveDao = retrieveDao;
    }

    /**
     * 查找全部评论
     */
    public PageResult<Comment> find() {
        return retrieveDao.findAll();
    }

    /**
     * 根据博客id查找评论
     */
    public PageResult<Comment> findByBlogId(String blogId) {
        return retrieveDao.findByBlogId(blogId);
    }
    /**
     * 根据博客id查找评论
     **/
    public Comment findById(String id) {
        return retrieveDao.findById(id);
    }

    /**
     * 查找全部留言
     **/
    public PageResult<Comment> findAllMessage() {
        return retrieveDao.findMessage();
    }

}
