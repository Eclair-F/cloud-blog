package com.qw.consumerexternal.feign;

import com.qw.consumerexternal.feign.callback.BlogFeignCallback;
import org.bson.types.ObjectId;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * @Program: cloud-blog
 * @ClassName BlogFeign
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-12 17:02
 * @Version 1.0
 **/
@Service
@FeignClient(name = "provider-blog",fallback = BlogFeignCallback.class)
public interface BlogFeign {


    /**
     * 查找所有博客
     */
    @GetMapping(value = "/{size}")
    Object blog(@PathVariable(value = "size") int size);

    /**
     * 查找所有博客
     * 上一页
     */
    @GetMapping(value = "/up/{size}/{id}")
    Object upBlog(@PathVariable(value = "size") int size, @PathVariable(value = "id") ObjectId id);

    /**
     * 查找所有博客
     * 下一页
     */
    @GetMapping(value = "/down/{size}/{id}")
    Object downBlog(@PathVariable(value = "size") int size, @PathVariable(value = "id") ObjectId id);

      /**
     * 根据search查找博客
     */
    @GetMapping(value = "/search/{size}/{search}")
    Object findSearch(@PathVariable(value = "size") int size, @PathVariable(value = "search") String search);

    /**
     * 上一页
     * 根据search查找博客
     */
    @GetMapping(value = "/search/up/{size}/{search}/{id}")
    Object upSearch(@PathVariable(value = "size") int size, @PathVariable(value = "search") String search, @PathVariable(value = "id") ObjectId id);

    /**
     * 下一页
     * 根据search查找博客
     */
    @GetMapping(value = "/search/down/{size}/{search}/{id}")
    Object downSearch(@PathVariable(value = "size") int size, @PathVariable(value = "search") String search, @PathVariable(value = "id") ObjectId id);


    /**
     * 根据类别查找博客
     */
    @GetMapping(value = "/category/{size}/{category}")
    Object findCategory(@PathVariable(value = "size") int size, @PathVariable(value = "category") String category);

    /**
     * 上一页
     * 根据类别查找博客
     */
    @GetMapping(value = "/category/up/{size}/{category}/{id}")
    Object upCategory(@PathVariable(value = "size") int size, @PathVariable(value = "category") String category, @PathVariable(value = "id") ObjectId id);

    /**
     * 下一页
     * 根据类别查找博客
     */
    @GetMapping(value = "/category/down/{size}/{category}/{id}")
    Object downCategory(@PathVariable(value = "size") int size, @PathVariable(value = "category") String category, @PathVariable(value = "id") ObjectId id);

    /**
     * 根据id查找博客
     */
    @GetMapping(value = "/id/{id}")
    Object findById(@PathVariable(value = "id") String id);

    /**
     * 添加博客
     */
    @PostMapping(value = "/")
    boolean saveBlog(@RequestBody Object blog);

    /**
     * 存为草稿
     **/
    @PutMapping(value = "/{id}")
    boolean blogToDraft(@PathVariable(value = "id") String id);

    /**
     * 修改博客
     */
    @PutMapping(value = "/")
    boolean update(@RequestBody Object blog);

    /**
     * 修改博客信息
     */
    @PutMapping(value = "/information")
    boolean updateBlog(@RequestBody Object blog);


    /**
     * 删除博客
     */
    @DeleteMapping(value = "/{id}")
    boolean deleteBlog(@PathVariable(value = "id") String id);


    /**
     * 查找所有草稿
     */
    @GetMapping(value = "/draft/{size}")
    Object draft(@PathVariable(value = "size") int size);

    /**
     * 查找所有草稿
     */
    @GetMapping(value = "/draft/up/{size}/{id}")
    Object upDraft(@PathVariable(value = "size") int size, @PathVariable(value = "id") ObjectId id);

    /**
     * 查找所有草稿
     */
    @GetMapping(value = "/draft/down/{size}/{id}")
    Object downDraft(@PathVariable(value = "size") int size, @PathVariable(value = "id") ObjectId id);


    /**
     * 题目
     * 根据search查找草稿
     */
    @GetMapping(value = "/draft/search/{size}/{search}")
    Object findDraftSearch(@PathVariable(value = "size") int size, @PathVariable(value = "search") String search);


    /**
     * 题目
     * 根据search查找草稿
     */
    @GetMapping(value = "/draft/search/up/{size}/{search}/{id}")
    Object upDraftSearch(@PathVariable(value = "size") int size, @PathVariable(value = "search") String search, @PathVariable(value = "id") ObjectId id);

    /**
     * 题目
     * 根据search查找草稿
     */
    @GetMapping(value = "/draft/search/down/{size}/{search}/{id}")
    Object downDraftSearch(@PathVariable(value = "size") int size, @PathVariable(value = "search") String search, @PathVariable(value = "id") ObjectId id);

    /**
     * 题目
     * 根据类别查找草稿
     */
    @GetMapping(value = "/draft/category/{size}/{category}")
    Object findDraftCategory(@PathVariable(value = "size") int size, @PathVariable(value = "category") String category);

    /**
     * 题目
     * 根据类别查找草稿
     */
    @GetMapping(value = "/draft/category/up/{size}/{category}/{id}")
    Object upDraftCategory(@PathVariable(value = "size") int size, @PathVariable(value = "category") String category, @PathVariable(value = "id") ObjectId id);

    /**
     * 题目
     * 根据类别查找草稿
     */
    @GetMapping(value = "/draft/category/down/{size}/{category}/{id}")
    Object downDraftCategory(@PathVariable(value = "size") int size, @PathVariable(value = "category") String category, @PathVariable(value = "id") ObjectId id);


    /**
     * 添加草稿
     */
    @PostMapping(value = "/draft")
    boolean saveDraft(@RequestBody Object draft);

    /**
     * 草稿发布
     */
    @PutMapping(value = "/draft/{id}")
    boolean draftToBlog(@PathVariable(value = "id") String id);


}
