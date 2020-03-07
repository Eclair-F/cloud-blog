package com.qw.consumerexternal.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * @author Eclair
 */
@FeignClient(name = "provider-essay")
public interface EssayFeign {

    /**
     * 查找所有随笔
     */
    @GetMapping(value = "/")
    Object essay() ;

    /**
     * 根据时间查找随笔
     */
    @GetMapping(value = "/date/{date}")
    Object findByLGDate(@PathVariable String date);

    /**
     * 添加随笔
     */
    @PostMapping(value = "/")
    boolean saveEssay(@RequestBody Object essay);


    /**
     * 删除随笔
     */
    @DeleteMapping(value = "/{id}")
    boolean deleteEssay(@PathVariable(value = "id") String id);


}
