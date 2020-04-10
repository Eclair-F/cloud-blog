package com.qw.provideressay.services;

import com.qw.provideressay.entity.Essay;
import com.qw.provideressay.entity.PageResult;

/**
 * @Program: cloud-blog
 * @ClassName EssayService
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-27 23:27
 * @Version 1.0
 **/
public interface EssayService {

    /**
     * 添加随笔
     */
    boolean createEssay(Essay essay);

    /**
     * 根据id删除随笔
     */
    boolean deleteById(String id);
    /**
     * 查找所有随笔
     */
    PageResult<Essay> findAllEssay();

}
