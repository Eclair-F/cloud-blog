package com.qw.providerblog.controller.implement;

import com.qw.providerblog.controller.DraftController;
import com.qw.providerblog.entity.Blog;
import com.qw.providerblog.entity.PageResult;
import com.qw.providerblog.services.BlogService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Program: cloud-blog
 * @ClassName DraftControllerImpl
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-28 15:10
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/draft")
public class DraftControllerImpl implements DraftController {
    private final BlogService draftService;
    private final HttpServletRequest request;
    private final String Admin = "admin";

    private final String TRUE = "true";

    public DraftControllerImpl(BlogService draftService, HttpServletRequest request) {
        this.draftService = draftService;
        this.request = request;
    }


    private String getRole() {
        return (String) request.getAttribute("role");
    }


    /**
     * 查找所有草稿
     */
    @Override
    @GetMapping(value = "/{size}")
    public PageResult<Blog> draft(@PathVariable int size) {
        if (Admin.equals(getRole())) {
            return draftService.findAllBlog(size, TRUE);
        }
        return null;
    }

    /**
     * 查找所有草稿
     */
    @Override
    @GetMapping(value = "/up/{size}/{id}")
    public PageResult<Blog> upDraft(@PathVariable int size, @PathVariable ObjectId id) {
        if (Admin.equals(getRole())) {
            return draftService.upBlog(size, id, TRUE);
        }
        return null;
    }

    /**
     * 查找所有草稿
     */
    @Override
    @GetMapping(value = "/down/{size}/{id}")
    public PageResult<Blog> downDraft(@PathVariable int size, @PathVariable ObjectId id) {
        if (Admin.equals(getRole())) {
            return draftService.downBlog(size, id, TRUE);
        }
        return null;
    }


    /**
     * 题目
     * 根据search查找草稿
     */
    @Override
    @GetMapping(value = "/search/{size}/{search}")
    public PageResult<Blog> findDraftSearch(@PathVariable int size, @PathVariable String search) {
        if (Admin.equals(getRole())) {
            return draftService.findSearch(size, search, TRUE);
        }
        return null;
    }


    /**
     * 题目
     * 根据search查找草稿
     */
    @Override
    @GetMapping(value = "/search/up/{size}/{search}/{id}")
    public PageResult<Blog> upDraftSearch(@PathVariable int size, @PathVariable String search, @PathVariable ObjectId id) {
        if (Admin.equals(getRole())) {
            return draftService.upSearch(size, search, id, TRUE);
        }
        return null;
    }

    /**
     * 题目
     * 根据search查找草稿
     */
    @Override
    @GetMapping(value = "/search/down/{size}/{search}/{id}")
    public PageResult<Blog> downDraftSearch(@PathVariable int size, @PathVariable String search, @PathVariable ObjectId id) {
        if (Admin.equals(getRole())) {
            return draftService.downSearch(size, search, id, TRUE);
        }
        return null;
    }


    /**
     * 题目
     * 根据类别查找草稿
     */
    @Override
    @GetMapping(value = "/category/{size}/{category}")
    public PageResult<Blog> findDraftCategory(@PathVariable int size, @PathVariable String category) {
        if (Admin.equals(getRole())) {
            return draftService.findCategory(size, category, TRUE);
        }
        return null;
    }


    /**
     * 题目
     * 根据类别查找草稿
     */
    @Override
    @GetMapping(value = "/category/up/{size}/{category}/{id}")
    public PageResult<Blog> upDraftCategory(@PathVariable int size, @PathVariable String category, @PathVariable ObjectId id) {
        if (Admin.equals(getRole())) {
            return draftService.upCategory(size, category, id, TRUE);
        }
        return null;
    }

    /**
     * 题目
     * 根据类别查找草稿
     */
    @Override
    @GetMapping(value = "/category/down/{size}/{category}/{id}")
    public PageResult<Blog> downDraftCategory(@PathVariable int size, @PathVariable String category, @PathVariable ObjectId id) {
        if (Admin.equals(getRole())) {
            return draftService.downCategory(size, category, id, TRUE);
        }
        return null;
    }


    /**
     * 添加草稿
     */
    @Override
    @PostMapping(value = "")
    public boolean saveDraft(@RequestBody Blog draft) {
        if (Admin.equals(getRole())) {
            return draftService.createDraft(draft);
        }
        return false;
    }

    /**
     * 草稿发布
     */
    @Override
    @PutMapping(value = "/{id}")
    public boolean draftToBlog(@PathVariable String id) {
        if (Admin.equals(getRole())) {
            return draftService.draftToBlog(id);
        }
        return false;
    }

}
