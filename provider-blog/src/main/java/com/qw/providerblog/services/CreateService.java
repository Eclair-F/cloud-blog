package com.qw.providerblog.services;

import com.qw.providerblog.dao.CreateDao;
import com.qw.providerblog.entity.Blog;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Program: cloud-blog
 * @ClassName CreateService
 * @Description:
 * @Author: Eclair
 * @Create: 2020-01-14 15:28
 * @Version 1.0
 **/
@Service
public class CreateService {
    private final CreateDao createDao;

    public CreateService(CreateDao createDao) {
        this.createDao = createDao;

    }

    /**
     * 添加博客
     */
    public boolean createBlog(Blog blog) {
        blog.setCollect(0);
        blog.setPraise(0);
        blog.setView(0);
        blog.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        return createDao.createBlog(blog) != null;
    }
    /**
     * 添加草稿
     */
    public boolean createDraft(Blog blog) {
        blog.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        blog=createDao.createBlog(blog);
        return createDao.createDraft(blog.getId()) != null;
    }
}
