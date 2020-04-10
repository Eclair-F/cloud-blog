package com.qw.provideressay.controller;


import com.qw.provideressay.entity.Essay;
import com.qw.provideressay.entity.PageResult;


/**
 * @author Eclair
 */

public interface EssayController {



    /**
     * 查找所有随笔
     */

    PageResult<Essay> essay();


    /**
     * 添加随笔
     */

    boolean saveEssay(Essay essay) ;


    /**
     * 删除随笔
     */

    boolean deleteEssay( String id) ;

}
