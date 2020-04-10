package com.qw.providerblog.controller.implement;

import com.qw.providerblog.controller.BlogController;
import com.qw.providerblog.entity.Blog;
import com.qw.providerblog.entity.PageResult;
import com.qw.providerblog.services.BlogService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Program: cloud-blog
 * @ClassName BlogControllerImpl
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-28 15:09
 * @Version 1.0
 **/
@RestController
public class BlogControllerImpl implements BlogController {

    private final BlogService blogService;
    private final HttpServletRequest request;
    private final String Admin = "admin";
    private final String FALSE = "false";

    public BlogControllerImpl(BlogService blogService,
                              HttpServletRequest request) {
        this.blogService = blogService;
        this.request = request;

    }

    private String getRole() {
        return (String) request.getAttribute("role");
    }

    /**
     * 查找所有博客
     */
    @Override
    @GetMapping(value = "/{size}")
    public PageResult<Blog> blog(@PathVariable int size) {

        return blogService.findAllBlog(size, FALSE);
    }

    /**
     * 查找所有博客
     * 上一页
     */
    @Override
    @GetMapping(value = "/up/{size}/{id}")
    public PageResult<Blog> upBlog(@PathVariable int size, @PathVariable ObjectId id) {
        return blogService.upBlog(size, id, FALSE);
    }

    /**
     * 查找所有博客
     * 下一页
     */
    @Override
    @GetMapping(value = "/down/{size}/{id}")
    public PageResult<Blog> downBlog(@PathVariable int size, @PathVariable ObjectId id) {
        return blogService.downBlog(size, id, FALSE);
    }


    /**
     * 根据search查找博客
     */
    @Override
    @GetMapping(value = "/search/{size}/{search}")
    public PageResult<Blog> findSearch(@PathVariable int size, @PathVariable String search) {
        return blogService.findSearch(size, search, FALSE);
    }


    /**
     * 上一页
     * 根据search查找博客
     */
    @Override
    @GetMapping(value = "/search/up/{size}/{search}/{id}")
    public PageResult<Blog> upSearch(@PathVariable int size, @PathVariable String search, @PathVariable ObjectId id) {
        return blogService.upSearch(size, search, id, FALSE);
    }

    /**
     * 下一页
     * 根据search查找博客
     */
    @Override
    @GetMapping(value = "/search/down/{size}/{search}/{id}")
    public PageResult<Blog> downSearch(@PathVariable int size, @PathVariable String search, @PathVariable ObjectId id) {
        return blogService.downSearch(size, search, id, FALSE);
    }


    /**
     * 根据类别查找博客
     */
    @Override
    @GetMapping(value = "/category/{size}/{category}")
    public PageResult<Blog> findCategory(@PathVariable int size, @PathVariable String category) {
        return blogService.findCategory(size, category, FALSE);
    }


    /**
     * 上一页
     * 根据类别查找博客
     */
    @Override
    @GetMapping(value = "/category/up/{size}/{category}/{id}")
    public PageResult<Blog> upCategory(@PathVariable int size, @PathVariable String category, @PathVariable ObjectId id) {
        return blogService.upCategory(size, category, id, FALSE);
    }

    /**
     * 下一页
     * 根据类别查找博客
     */
    @Override
    @GetMapping(value = "/category/down/{size}/{category}/{id}")
    public PageResult<Blog> downCategory(@PathVariable int size, @PathVariable String category, @PathVariable ObjectId id) {
        return blogService.downCategory(size, category, id, FALSE);
    }


    /**
     * 根据id查找博客
     */
    @Override
    @GetMapping(value = "/id/{id}")
    public Blog findById(@PathVariable String id) {
        return blogService.findById(id);
    }

    /**
     * 添加博客
     */
    @Override
    @PostMapping(value = "/")
    public boolean saveBlog(@RequestBody Blog blog) {
        if (Admin.equals(getRole())) {
            return blogService.createBlog(blog);
        }
        return false;
    }

    /**
     * 存为草稿
     **/
    @Override
    @PutMapping(value = "/{id}")
    public boolean blogToDraft(@PathVariable String id) {
        if (Admin.equals(getRole())) {
            return blogService.blogToDraft(id);
        }
        return false;
    }

    /**
     * 修改博客
     */
    @Override
    @PutMapping(value = "/")
    public boolean update(@RequestBody Blog blog) {
        if (Admin.equals(getRole())) {
            return blogService.updateBlog(blog);
        }
        return false;
    }

    /**
     * 修改博客信息
     */
    @Override
    @PutMapping(value = "/information")
    public boolean updateBlogCollect(@RequestBody Blog blog) {
        return blogService.updateBlogInfo(blog);
    }


    /**
     * 删除博客
     */
    @Override
    @DeleteMapping(value = "/{id}")
    public boolean deleteBlog(@PathVariable String id) {
        if (Admin.equals(getRole())) {
            return blogService.deleteById(id);
        }
        return false;
    }
}
