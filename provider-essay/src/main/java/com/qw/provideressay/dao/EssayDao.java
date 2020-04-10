package com.qw.provideressay.dao;

import com.mongodb.client.result.DeleteResult;
import com.qw.provideressay.entity.Essay;
import com.qw.provideressay.entity.PageResult;

/**
 * @Program: cloud-blog
 * @ClassName EssayDao
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-27 23:28
 * @Version 1.0
 **/
public interface EssayDao {
    /**
     * 创建
     * */
    Essay createEssay(Essay essay);
    /**
     * 删除随笔
     *
     * */
    DeleteResult deleteById(String id) ;

    /**
     * 查找所有随笔
     */
    PageResult<Essay> findAll() ;

}
