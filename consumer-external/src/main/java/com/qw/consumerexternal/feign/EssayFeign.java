package com.qw.consumerexternal.feign;

import com.qw.consumerexternal.feign.callback.EssayFeignCallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


/**
 * @author Eclair
 */
@Service
@FeignClient(name = "provider-essay",fallback = EssayFeignCallback.class)
public interface EssayFeign {
    /**
     * 查找所有随笔
     */
    @GetMapping(value = "/")
   Object essay();

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
