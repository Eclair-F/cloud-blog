package com.qw.consumerexternal.controller.implement;

import com.qw.consumerexternal.controller.BlogController;
import com.qw.consumerexternal.feign.BlogFeign;
import com.qw.consumerexternal.result.Result;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Program: cloud-blog
 * @ClassName BlogController
 * @Description:
 * @Author: Eclair
 * @Create: 2020-01-14 15:30
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/blog")
public class BlogControllerImpl implements BlogController {
    private final BlogFeign blogFeign;
    private final HttpServletRequest request;

    public BlogControllerImpl(BlogFeign blogFeign, HttpServletRequest request) {
        this.blogFeign = blogFeign;
        this.request = request;
    }


    private boolean Admin() {
        String admin = "admin";
        return admin.equals(request.getAttribute("role"));
    }


    /**
     * 查找所有博客
     */
    @Override
    @GetMapping(value = "/{size}")
    public Result blog(@PathVariable int size) {
        Object blog = blogFeign.blog(size);
        if (blog != null) {
            return new Result(true, "查询成功", blog);
        }
        return new Result(false, "查询失败");
    }

    /**
     * 查找所有博客
     * 上一页
     */
    @Override
    @GetMapping(value = "/up/{size}/{id}")
    public Result upBlog(@PathVariable int size, @PathVariable ObjectId id) {
        Object blog = blogFeign.upBlog(size, id);
        if (blog != null) {
            return new Result(true, "查询成功", blog);
        }
        return new Result(false, "查询失败");
    }

    /**
     * 查找所有博客
     * 下一页
     */
    @Override
    @GetMapping(value = "/down/{size}/{id}")
    public Result downBlog(@PathVariable int size, @PathVariable ObjectId id) {
        Object blog = blogFeign.downBlog(size, id);
        if (blog != null) {
            return new Result(true, "查询成功", blog);
        }
        return new Result(false, "查询失败");
    }


    /**
     * 根据search查找博客
     */
    @Override
    @GetMapping(value = "/search/{size}/{search}")
    public Result findSearch(@PathVariable int size, @PathVariable String search) {
        Object blog = blogFeign.findSearch(size, search);
        if (blog != null) {
            return new Result(true, "查询成功", blog);
        }
        return new Result(false, "查询失败");
    }


    /**
     * 上一页
     * 根据search查找博客
     */
    @Override
    @GetMapping(value = "/search/up/{size}/{search}/{id}")
    public Result upSearch(@PathVariable int size, @PathVariable String search, @PathVariable ObjectId id) {
        Object blog = blogFeign.upSearch(size, search, id);
        if (blog != null) {
            return new Result(true, "查询成功", blog);
        }
        return new Result(false, "查询失败");
    }

    /**
     * 下一页
     * 根据search查找博客
     */
    @Override
    @GetMapping(value = "/search/down/{size}/{search}/{id}")
    public Result downSearch(@PathVariable int size, @PathVariable String search, @PathVariable ObjectId id) {
        Object blog = blogFeign.downSearch(size, search, id);
        if (blog != null) {
            return new Result(true, "查询成功", blog);
        }
        return new Result(false, "查询失败");
    }


    /**
     * 根据类别查找博客
     */
    @Override
    @GetMapping(value = "/category/{size}/{category}")
    public Result findCategory(@PathVariable int size, @PathVariable String category) {
        Object blog = blogFeign.findCategory(size, category);
        if (blog != null) {
            return new Result(true, "查询成功", blog);
        }
        return new Result(false, "查询失败");
    }


    /**
     * 上一页
     * 根据类别查找博客
     */
    @Override
    @GetMapping(value = "/category/up/{size}/{category}/{id}")
    public Result upCategory(@PathVariable int size, @PathVariable String category, @PathVariable ObjectId id) {
        Object blog = blogFeign.upCategory(size, category, id);
        if (blog != null) {
            return new Result(true, "查询成功", blog);
        }
        return new Result(false, "查询失败");
    }

    /**
     * 下一页
     * 根据类别查找博客
     */
    @Override
    @GetMapping(value = "/category/down/{size}/{category}/{id}")
    public Result downCategory(@PathVariable int size, @PathVariable String category, @PathVariable ObjectId id) {
        Object blog = blogFeign.downCategory(size, category, id);
        if (blog != null) {
            return new Result(true, "查询成功", blog);
        }
        return new Result(false, "查询失败");
    }


    /**
     * 根据id查找博客
     */
    @Override
    @GetMapping(value = "/id/{id}")
    public Result findById(@PathVariable String id) {
        Object blog = blogFeign.findById(id);
        if (blog != null) {
            return new Result(true, "查询成功", blog);
        }
        return new Result(false, "查询失败");
    }

    /**
     * 添加博客
     */
    @Override
    @PostMapping(value = "")
    public Result saveBlog(@RequestBody Object blog) {
        if (Admin()) {
            boolean add = blogFeign.saveBlog(blog);
            if (add) {
                return new Result(true, "添加成功");
            }
        }
        return new Result(false, "添加失败");
    }

    /**
     * 存为草稿
     */
    @Override
    @PutMapping(value = "/{id}")
    public Result blogToDraft(@PathVariable String id) {
        if (Admin()) {
            boolean change = blogFeign.blogToDraft(id);
            if (change) {
                return new Result(true, "存储成功");
            }
        }
        return new Result(false, "存储失败");
    }

    /**
     * 修改博客
     */
    @Override
    @PutMapping(value = "")
    public Result update(@RequestBody Object blog) {
        if (Admin()) {
            boolean update = blogFeign.update(blog);
            if (update) {
                return new Result(true, "修改成功");
            }
        }
        return new Result(false, "修改失败");
    }

    /**
     * 修改博客信息
     */
    @Override
    @PutMapping(value = "/information")
    public Result updateBlogCollect(@RequestBody Object blog) {
        boolean update = blogFeign.updateBlog(blog);
        if (update) {
            return new Result(true, "修改成功");
        }
        return new Result(false, "修改失败");
    }


    /**
     * 删除博客
     */
    @Override
    @DeleteMapping(value = "/{id}")
    public Result deleteBlog(@PathVariable String id) {
        if (Admin()) {
            boolean delete = blogFeign.deleteBlog(id);
            if (delete) {
                return new Result(true, "删除成功");
            }
        }
        return new Result(false, "删除失败");
    }


}