package com.qw.providerblog.controller;

import com.qw.providerblog.entity.Blog;
import com.qw.providerblog.entity.Image;
import com.qw.providerblog.entity.PageResult;
import com.qw.providerblog.services.CreateService;
import com.qw.providerblog.services.DeleteService;
import com.qw.providerblog.services.RetrieveService;
import com.qw.providerblog.services.UpdateService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Program: cloud-blog
 * @ClassName BlogController
 * @Description:
 * @Author: Eclair
 * @Create: 2020-01-14 15:30
 * @Version 1.0
 **/

@RestController
public class BlogController {
    private final CreateService createService;
    private final RetrieveService retrieveService;
    private final UpdateService updateService;
    private final DeleteService deleteService;
    private final HttpServletRequest request;
    private final String Admin = "admin";
    private String Role;

    /**
     * 头像保存路径
     */
    public static final String WINDOWS_PROFILES_PATH = "C:/super_meeting/profiles/";
    public static final String LINUX_PROFILES_PATH = "/root/super_meeting/profiles/";

    public BlogController(RetrieveService retrieveService, CreateService createService, DeleteService deleteService,
                          UpdateService updateService, HttpServletRequest request) {
        this.retrieveService = retrieveService;
        this.createService = createService;
        this.deleteService = deleteService;
        this.updateService = updateService;
        this.request = request;
    }


    private String getRole() {
        return (String) request.getAttribute("role");
    }

    private String getOSPath() {
        // 根据Windows和Linux配置不同的头像保存路径
        String OSName = System.getProperty("os.name");
        return OSName.toLowerCase().startsWith("win") ? WINDOWS_PROFILES_PATH
                : LINUX_PROFILES_PATH;
    }

    private PageResult<Blog> blogPageResult(PageResult<Blog> pageResult) {
        if (pageResult.getRows().isEmpty()) {
            return null;
        } else {
            return pageResult;
        }
    }

    /**
     * 查找所有博客
     */
    @GetMapping(value = "/")
    public PageResult<Blog> blog() {
        return blogPageResult(retrieveService.findAllBlog(false));
    }

    /**
     * 查找所有博客
     */
    @GetMapping(value = "/down/{id}")
    public PageResult<Blog> blogNext(@PathVariable String id) {
        return blogPageResult(retrieveService.findAllBlog(id, false));
    }


    /**
     * 根据id查找博客
     */
    @GetMapping(value = "/{id}")
    public Blog findById(@PathVariable String id) {
        return retrieveService.findById(id);
    }

    /**
     * 类别
     * 根据category查找博客
     */
    @GetMapping(value = "/category/{category}")
    public PageResult<Blog> findByCategory(@PathVariable String category) {
        return blogPageResult(retrieveService.findByCategory(category, false));
    }

    /**
     * 题目
     * 根据title查找博客
     */
    @GetMapping(value = "/title/{title}")
    public PageResult<Blog> findByTitle(@PathVariable String title) {
        return blogPageResult(retrieveService.findByTitle(title, false));
    }

    /**
     * 内容
     * 根据content查找博客
     */
    @GetMapping(value = "/content/{content}")
    public PageResult<Blog> findByContent(@PathVariable String content) {
        return blogPageResult(retrieveService.findByContent(content, false));
    }

    /**
     * 内容或题目
     * 根据text查找博客
     */
    @GetMapping(value = "/text/{text}")
    public PageResult<Blog> findByText(@PathVariable String text) {
        return blogPageResult(retrieveService.findByText(text, false));
    }

    /**
     * 根据时间查找博客
     */
    @GetMapping(value = "/date/{date}")
    public PageResult<Blog> findByLGDate(@PathVariable String date) {
        return blogPageResult(retrieveService.findByLGDate(date, date + 1, false));
    }


    /**
     * 查找草稿
     */
    @GetMapping(value = "/draft")
    public PageResult<Blog> findDraft() {
        if (Admin.equals(getRole())) {
            return blogPageResult(retrieveService.findDraft());
        }
        return null;
    }

    /**
     * @param response:
     * @return void
     * @Author Eclair
     * @Description //获取图片
     * @Date 2020/3/6 16:50
     * @Param @param id:
     **/
    @GetMapping(value = "/image/{blogId}/{imageId}")
    public void findImage(@PathVariable String blogId,@PathVariable String imageId, HttpServletResponse response) {
        String proPath = getOSPath();
        String path = proPath + blogId + "/" + imageId;
        response = retrieveService.findImage(path, response);
    }


    /**
     * 添加博客
     */
    @PostMapping(value = "/")
    public boolean saveBlog(@RequestBody Blog blog) {
        if (Admin.equals(getRole())) {
            return createService.createBlog(blog);
        }
        return false;
    }

    /**
     * 添加草稿
     */
    @PostMapping(value = "/draft")
    public boolean saveDraft(@RequestBody Blog blog) {
        if (Admin.equals(getRole())) {
            return createService.createDraft(blog);
        }
        return false;
    }

    /**
     * 修改博客
     */
    @PutMapping(value = "/")
    public boolean update(@RequestBody Blog blog) {
        if (Admin.equals(getRole())) {
            return updateService.updateBlog(blog);
        }
        return false;
    }

    @PutMapping(value = "/image/{id}")
    public boolean updateImage(@RequestParam("image") MultipartFile image,@PathVariable String id) throws IOException {
        if (Admin.equals(getRole())) {
            if (!image.isEmpty()) {
                String proPath = getOSPath();
                String name = image.getOriginalFilename();
                String imageName = new ObjectId().toHexString().concat(name.substring(name.lastIndexOf(".")));
                String path = proPath +id + "/";
                updateService.updateImage(image, path, imageName);
                return true;
            }
        }
        return false;
    }

    /**
     * 修改博客信息
     */
    @PutMapping(value = "/information")
    public boolean updateBlogCollect(@RequestBody Blog blog) {
        if (Admin.equals(getRole())) {
            return updateService.updateBlogInfo(blog);
        }
        return false;
    }


    /**
     * 删除博客
     */
    @DeleteMapping(value = "/{id}")
    public boolean deleteBlog(@PathVariable String id) {
        if (Admin.equals(getRole())) {
            return deleteService.deleteById(id);
        }
        return false;
    }


    /**
     * 查找所有删除博客
     */
    @GetMapping(value = "/delete")
    public PageResult<Blog> blogIsDelete() {
        if (Admin.equals(getRole())) {
            return blogPageResult(retrieveService.findAllBlog(true));
        }
        return null;
    }

    /**
     * 根据类别查找博客
     */
    @GetMapping(value = "/delete/category/{category}")
    public PageResult<Blog> findIsDeleteByCategory(@PathVariable String category) {
        if (Admin.equals(getRole())) {
            return blogPageResult(retrieveService.findByCategory(category, true));
        }
        return null;
    }

    /**
     * 博客还原
     */
    @PutMapping(value = "/delete/{id}")
    public boolean recoveryBlog(@PathVariable String id) {
        if (Admin.equals(getRole())) {
            return updateService.recoveryById(id);
        }
        return false;
    }

    /**
     * 博客发布
     */
    @PutMapping(value = "/draft/{id}")
    public boolean draft(@PathVariable String id) {
        if (Admin.equals(getRole())) {
            return updateService.Draft(id);
        }
        return false;
    }
}
