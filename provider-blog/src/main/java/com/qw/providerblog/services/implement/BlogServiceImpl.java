package com.qw.providerblog.services.implement;

import com.qw.providerblog.dao.BlogDao;
import com.qw.providerblog.entity.Blog;
import com.qw.providerblog.entity.PageResult;
import com.qw.providerblog.services.BlogService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Program: cloud-blog
 * @ClassName BlogServiceImpl
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-28 14:48
 * @Version 1.0
 **/
@Service
public class BlogServiceImpl implements BlogService {
    private final BlogDao blogDao;

    public BlogServiceImpl(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    private PageResult<Blog> blogPageResult(PageResult<Blog> pageResult) {
        if (pageResult.getRows().isEmpty()) {
            return null;
        } else {
            return pageResult;
        }
    }

    /**
     * 添加博客
     */
    @Override
    public boolean createBlog(Blog blog) {
        blog.setCommentNum(0);
        blog.setPraise(0);
        blog.setView(0);
        blog.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        blog.setDraft("false");
        return blogDao.createBlog(blog) != null;
    }

    /**
     * 添加草稿
     */
    @Override
    public boolean createDraft(Blog blog) {
        blog.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        blog.setDraft("true");
        return blogDao.createBlog(blog) != null;
    }


    /**
     * 根据id删除博客
     */
    @Override
    public boolean deleteById(String id) {
        return blogDao.deleteById(id).getDeletedCount() != 0;
    }

    /**
     * 查找所有博客
     */
    @Override
    public PageResult<Blog> findAllBlog(int size, String bool) {
        return blogPageResult(blogDao.findAll(size, bool));
    }

    /**
     * 查找所有博客
     */
    @Override
    public PageResult<Blog> upBlog(int size, ObjectId id, String bool) {
        return blogPageResult(blogDao.up(size, id, bool));
    }

    /**
     * 查找所有博客
     */
    @Override
    public PageResult<Blog> downBlog(int size, ObjectId id, String bool) {
        return blogPageResult(blogDao.down(size, id, bool));
    }

    /**
     * 根据id查找博客
     */
    @Override
    public Blog findById(String id) {
        return blogDao.findById(id);
    }


    /**
     * 根据内容查找博客
     */
    @Override
    public PageResult<Blog> findSearch(int size, String search, String bool) {
        return blogPageResult(blogDao.findBySearch(size, search, bool));
    }

    /**
     * 根据内容查找博客
     * 上一页
     */
    @Override
    public PageResult<Blog> upSearch(int size, String search, ObjectId id, String bool) {
        return blogPageResult(blogDao.upSearch(size, search, id, bool));
    }

    /**
     * 根据内容查找博客
     * 下一页
     */
    @Override
    public PageResult<Blog> downSearch(int size, String search, ObjectId id, String bool) {
        return blogPageResult(blogDao.downSearch(size, search, id, bool));
    }

    /**
     * 根据类别查找博客
     */
    @Override
    public PageResult<Blog> findCategory(int size, String category, String bool) {
        return blogPageResult(blogDao.findByCategory(size, category, bool));
    }

    /**
     * 根据类别查找博客
     * 上一页
     */
    @Override
    public PageResult<Blog> upCategory(int size, String category, ObjectId id, String bool) {
        return blogPageResult(blogDao.upCategory(size, category, id, bool));
    }

    /**
     * 根据类别查找博客
     * 下一页
     */
    @Override
    public PageResult<Blog> downCategory(int size, String category, ObjectId id, String bool) {
        return blogPageResult(blogDao.downCategory(size, category, id, bool));
    }

    /**
     * 修改博客
     */
    @Override
    public boolean updateBlog(Blog blog) {
        if (blogDao.findById(blog.getId()) != null) {
            if (!blog.getTitle().isEmpty()) {
                blogDao.updateByTitle(blog.getId(), blog.getTitle());
            }
            if (!blog.getCategory().isEmpty()) {
                blogDao.updateByCategory(blog.getId(), blog.getCategory());
            }
            if (!blog.getContent().isEmpty()) {
                blogDao.updateByContent(blog.getId(), blog.getContent());
            }
            if (!blog.getCover().isEmpty()) {
                blogDao.updateByCover(blog.getId(), blog.getCover());
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * 修改博客信息
     */
    @Override
    public boolean updateBlogInfo(Blog blog) {
        if (blogDao.findById(blog.getId()) != null) {
            if (blog.getCommentNum() != null) {
                blogDao.updateByCommentNum(blog.getId(), blog.getCommentNum());
            }
            if (blog.getView() != null) {
                blogDao.updateByView(blog.getId(), blog.getView());
            }
            if (blog.getPraise() != null) {
                blogDao.updateByPraise(blog.getId(), blog.getPraise());
            }
            return true;
        } else {
            return false;
        }

    }

    /**
     * 博客发布
     **/
    @Override
    public boolean draftToBlog(String id) {
        return blogDao.draftToBlog(id).getModifiedCount() != 0;
    }

    /**
     * 存为草稿
     **/
    @Override
    public boolean blogToDraft(String id) {
        return blogDao.blogToDraft(id).getModifiedCount() != 0;
    }
}
