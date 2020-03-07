package com.qw.providerblog.services;

import com.qw.providerblog.dao.RetrieveDao;
import com.qw.providerblog.dao.UpdateDao;
import com.qw.providerblog.entity.Blog;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Program: cloud-blog
 * @ClassName UpdateService
 * @Description:
 * @Author: Eclair
 * @Create: 2020-01-14 15:29
 * @Version 1.0
 **/

@Service
public class UpdateService {
    private final UpdateDao updateDao;
    private final RetrieveDao retrieveDao;

    public UpdateService(UpdateDao updateDao, RetrieveDao retrieveDao) {
        this.updateDao = updateDao;
        this.retrieveDao = retrieveDao;
    }

    /**
     * 修改博客
     */
    public boolean updateBlog(Blog blog) {
        if (retrieveDao.findById(blog.getId()) != null) {
            if (!blog.getTitle().isEmpty()) {
                updateDao.updateByTitle(blog.getId(), blog.getTitle());
            }
            if (!blog.getCategory().isEmpty()) {
                updateDao.updateByCategory(blog.getId(), blog.getCategory());
            }
            if (!blog.getContent().isEmpty()) {
                updateDao.updateByContent(blog.getId(), blog.getContent());
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * 修改博客信息
     */
    public boolean updateBlogInfo(Blog blog) {
        if (retrieveDao.findById(blog.getId()) != null) {
            if (blog.getCollect()!=null) {
                updateDao.updateByCollect(blog.getId(), blog.getCollect());
            }
            if (blog.getView()!=null) {
                updateDao.updateByView(blog.getId(), blog.getView());
            }
            if (blog.getPraise()!=null) {
                updateDao.updateByPraise(blog.getId(), blog.getPraise());
            }
            return true;
        } else {
            return false;
        }

    }

    /**
     * @Author Eclair
     * @Description //上传图片
     * @Date 2020/3/6 17:10
     * @Param @param image:
     * @param path:
     * @param imageName:
     * @return boolean
     **/
    public boolean updateImage(MultipartFile image, String path, String imageName){

        if (!image.isEmpty()) {
            BufferedOutputStream out = null;
            try {
                File folder = new File(path);
                if (!folder.exists()) {
                    folder.mkdirs();
                }
                out = new BufferedOutputStream(new FileOutputStream(path + imageName));
                // 写入新文件
                out.write(image.getBytes());
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }

        return false;
    }

    /**
     * 博客找回
     */
    public boolean recoveryById(String id) {
        return updateDao.recoveryById(id).getModifiedCount() != 0;
    }
    /**
     * 博客发布
     **/
    public boolean Draft(String id) {
        return updateDao.Draft(id).getModifiedCount() != 0;
    }
}


