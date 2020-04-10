package com.qw.consumerexternal.controller.implement;


import com.qw.consumerexternal.controller.EssayController;
import com.qw.consumerexternal.feign.EssayFeign;
import com.qw.consumerexternal.result.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Eclair
 */
@RestController
@RequestMapping(value = "/essay")
public class EssayControllerImpl implements EssayController {

    private final EssayFeign essayFeign;
    private final HttpServletRequest request;

    public EssayControllerImpl(EssayFeign essayFeign, HttpServletRequest request) {
        this.essayFeign = essayFeign;
        this.request = request;
    }


    private boolean Admin() {
        String admin = "admin";
        return admin.equals(request.getAttribute("role"));
    }


    /**
     * 查找所有随笔
   */
    @Override
    @GetMapping(value = "")
    public Result essay() {
        Object essay = essayFeign.essay();
        if (essay != null) {
            return new Result(true, "查询成功", essay);
        }
        return new Result(false, "查询失败");
    }


    /**
     * 添加随笔
   */
    @Override
    @PostMapping(value = "")
    public Result saveEssay(@RequestBody Object essay) {
        if (Admin()) {
            boolean create = essayFeign.saveEssay(essay);
            if (create) {
                return new Result(true, "添加成功");
            }
        }
        return new Result(false, "添加失败");
    }


    /**
     * 删除随笔
   */
    @Override
    @DeleteMapping(value = "/{id}")
    public Result deleteEssay(@PathVariable String id) {
        if (Admin()) {
            boolean delete = essayFeign.deleteEssay(id);
            if (delete) {
                return new Result(true, "删除成功");
            }
        }
        return new Result(false, "删除失败");

    }


}
