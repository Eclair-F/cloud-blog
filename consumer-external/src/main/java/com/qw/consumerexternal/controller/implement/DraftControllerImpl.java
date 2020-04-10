package com.qw.consumerexternal.controller.implement;

import com.qw.consumerexternal.controller.DraftController;
import com.qw.consumerexternal.feign.BlogFeign;
import com.qw.consumerexternal.result.Result;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Program: cloud-blog
 * @ClassName DraftController
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-14 14:06
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/draft")
public class DraftControllerImpl implements DraftController {
    private final BlogFeign draftFeign;
    private final HttpServletRequest request;

    public DraftControllerImpl(BlogFeign blogFeign, HttpServletRequest request) {
        this.draftFeign = blogFeign;
        this.request = request;
    }


    private boolean Admin() {
        String admin = "admin";
        return admin.equals(request.getAttribute("role"));
    }

    /**
     * 查找所有草稿
     */

    @Override
    @GetMapping(value = "/{size}")
    public Result draft(@PathVariable int size) {
        if (Admin()) {
            Object draft = draftFeign.draft(size);
            if (draft != null) {
                return new Result(true, "查询成功", draft);
            }
        }
        return new Result(false, "查询失败");
    }

    /**
     * 查找所有草稿
     */
    @Override
    @GetMapping(value = "/up/{size}/{id}")
    public Result upDraft(@PathVariable int size, @PathVariable ObjectId id) {
        if (Admin()) {
            Object draft = draftFeign.upDraft(size, id);
            if (draft != null) {
                return new Result(true, "查询成功", draft);
            }
        }
        return new Result(false, "查询失败");

    }

    /**
     * 查找所有草稿
     */
    @Override
    @GetMapping(value = "/down/{size}/{id}")
    public Result downDraft(@PathVariable int size, @PathVariable ObjectId id) {
        if (Admin()) {
            Object draft = draftFeign.downDraft(size, id);
            if (draft != null) {
                return new Result(true, "查询成功", draft);
            }
        }
        return new Result(false, "查询失败");
    }


    /**
     * 题目
     * 根据search查找草稿
     */
    @Override
    @GetMapping(value = "/search/{size}/{search}")
    public Result findDraftSearch(@PathVariable int size, @PathVariable String search) {
        if (Admin()) {

            Object draft = draftFeign.findDraftSearch(size, search);
            if (draft != null) {
                return new Result(true, "查询成功", draft);
            }
        }
        return new Result(false, "查询失败");
    }


    /**
     * 题目
     * 根据search查找草稿
     */
    @Override
    @GetMapping(value = "/search/up/{size}/{search}/{id}")
    public Result upDraftSearch(@PathVariable int size, @PathVariable String search,
                                @PathVariable ObjectId id) {
        if (Admin()) {

            Object draft = draftFeign.upDraftSearch(size, search, id);
            if (draft != null) {
                return new Result(true, "查询成功", draft);
            }
        }
        return new Result(false, "查询失败");
    }

    /**
     * 题目
     * 根据search查找草稿
     */
    @Override
    @GetMapping(value = "/search/down/{size}/{search}/{id}")
    public Result downDraftSearch(@PathVariable int size, @PathVariable String search,
                                  @PathVariable ObjectId id) {
        if (Admin()) {

            Object draft = draftFeign.draft(size);
            if (draft != null) {
                return new Result(true, "查询成功", draft);
            }
        }
        return new Result(false, "查询失败");
    }


    /**
     * 题目
     * 根据类别查找草稿
     */
    @Override
    @GetMapping(value = "/category/{size}/{category}")
    public Result findDraftCategory(@PathVariable int size, @PathVariable String category) {
        if (Admin()) {

            Object draft = draftFeign.findDraftCategory(size, category);
            if (draft != null) {
                return new Result(true, "查询成功", draft);
            }
        }
        return new Result(false, "查询失败");
    }


    /**
     * 题目
     * 根据类别查找草稿
     */
    @Override
    @GetMapping(value = "/category/up/{size}/{category}/{id}")
    public Result upDraftCategory(@PathVariable int size,
                                  @PathVariable String category, @PathVariable ObjectId id) {
        if (Admin()) {
            Object draft = draftFeign.upDraftCategory(size, category, id);
            if (draft != null) {
                return new Result(true, "查询成功", draft);
            }
        }
        return new Result(false, "查询失败");
    }

    /**
     * 题目
     * 根据类别查找草稿
     */
    @Override
    @GetMapping(value = "/category/down/{size}/{category}/{id}")
    public Result downDraftCategory(@PathVariable int size,
                                    @PathVariable String category,
                                    @PathVariable ObjectId id) {
        if (Admin()) {
            Object draft = draftFeign.downDraftCategory(size, category, id);
            if (draft != null) {
                return new Result(true, "查询成功", draft);
            }
        }
        return new Result(false, "查询失败");
    }

    /**
     * 添加草稿
     */
    @Override
    @PostMapping(value = "")
    public Result saveBlog(@RequestBody Object draft) {
        if (Admin()) {
            boolean add = draftFeign.saveDraft(draft);
            if (add) {
                return new Result(true, "添加成功");
            }
        }
        return new Result(false, "添加失败");
    }


    /**
     * 草稿发布
     */
    @Override
    @PutMapping(value = "/{id}")
    public Result draftToBlog(@PathVariable String id) {
        if (Admin()) {
            boolean change = draftFeign.draftToBlog(id);
            if (change) {
                return new Result(true, "存储成功");
            }
        }
        return new Result(false, "存储失败");
    }


}