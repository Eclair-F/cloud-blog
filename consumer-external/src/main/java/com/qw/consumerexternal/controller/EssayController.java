package com.qw.consumerexternal.controller;


import com.qw.consumerexternal.result.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @author Eclair
 */

public interface EssayController {


    /**
     * 查找所有随笔
     */
    Result essay();

    /**
     * 添加随笔
     */
    Result saveEssay(@RequestBody Object essay) ;


    /**
     * 删除随笔
     */
    Result deleteEssay(@PathVariable String id) ;


}
