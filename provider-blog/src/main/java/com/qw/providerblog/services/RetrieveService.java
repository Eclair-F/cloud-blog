package com.qw.providerblog.services;

import com.qw.providerblog.dao.RetrieveDao;
import com.qw.providerblog.entity.Blog;
import com.qw.providerblog.entity.PageResult;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @Program: cloud-blog
 * @ClassName RetrieveService
 * @Description:
 * @Author: Eclair
 * @Create: 2020-01-14 15:29
 * @Version 1.0
 **/

@Service
public class RetrieveService {
    private final RetrieveDao retrieveDao;

    public RetrieveService(RetrieveDao retrieveDao) {
        this.retrieveDao = retrieveDao;
    }


    /**
     * 查找所有博客
     */
    public PageResult<Blog> findAllBlog(boolean bool) {
        return retrieveDao.findAllBlog(bool);
    }
    /**
     * 查找所有博客
     */
    public PageResult<Blog> findAllBlog(String id,boolean bool) {
        return retrieveDao.findAllBlog(id,bool);
    }
    /**
     * 根据id查找博客
     */
    public Blog findById(String id) {
        return retrieveDao.findById(id);
    }

    /**
     * 根据题目查找博客
     */
    public PageResult<Blog> findByTitle(String title, boolean bool) {
        return retrieveDao.findByTitle(title, bool);
    }

    /**
     * 根据内容查找博客
     */
    public PageResult<Blog> findByContent(String content, boolean bool) {
        return retrieveDao.findByContent(content, bool);
    }

    /**
     * 根据内容,题目查找博客
     */
    public PageResult<Blog> findByText(String text, boolean bool) {

        return retrieveDao.findByText(text, bool);
    }

    /**
     * 根据类别查找博客
     */
    public PageResult<Blog> findByCategory(String category, boolean bool) {
        return retrieveDao.findByCategory(category, bool);
    }

    /**
     * 根据时间查找博客
     * 小于等于
     */
    public PageResult<Blog> findByLTEDate(String date, boolean bool) {
        return retrieveDao.findByLTEDate(date, bool);
    }

    /**
     * 根据时间查找博客
     * 大于等于
     */
    public PageResult<Blog> findByGTEDate(String date, boolean bool) {
        return retrieveDao.findByGTEDate(date, bool);
    }

    /**
     * 根据时间查找博客
     * 时间区间查询
     */
    public PageResult<Blog> findByLGDate(String gtedate, String ltedate, boolean bool) {
        return retrieveDao.findByLGDate(gtedate, ltedate, bool);
    }
    /**
     * 查找草稿
     */
    public PageResult<Blog> findDraft()  {
        return retrieveDao.findDraft();
    }


    /**
     * 读取图片
     * @param path
     * @param response
     * @return
     */
    public HttpServletResponse findImage(String path , HttpServletResponse response) {

        try {
            System.out.println(path);
            FileInputStream hFile;
            hFile = new FileInputStream(path);
            //得到文件大小
            int i = hFile.available();
            byte[] data = new byte[i];
            //读数据
            hFile.read(data);
            response.setContentType("image/jpeg");
            OutputStream toClient = response.getOutputStream();
            //得到向客户端输出二进制数据的对象
            //输出数据
            toClient.write(data);
            toClient.flush();
            toClient.close();
            hFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}
