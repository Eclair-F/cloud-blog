package com.qw.consumerexternal.feign.callback;

import com.qw.consumerexternal.feign.EssayFeign;
import org.springframework.stereotype.Component;

/**
 * @Program: cloud-blog
 * @ClassName EssayFeignCallback
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-25 14:59
 * @Version 1.0
 **/
@Component
public class EssayFeignCallback implements EssayFeign {

    @Override
    public Object essay() {
        return null;
    }

    @Override
    public boolean saveEssay(Object essay) {
        return false;
    }

    @Override
    public boolean deleteEssay(String id) {
        return false;
    }
}
