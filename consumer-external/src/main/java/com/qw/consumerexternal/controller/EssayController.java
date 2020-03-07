package com.qw.consumerexternal.controller;


import com.qw.consumerexternal.feign.EssayFeign;
import com.qw.consumerexternal.result.Result;
import com.qw.consumerexternal.result.StatusCode;
import org.springframework.web.bind.annotation.*;


/**
 * @author Eclair
 */
@RestController
@RequestMapping(value = "/essay")
public class EssayController {

private final EssayFeign essayFeign;

    public EssayController(EssayFeign essayFeign) {
        this.essayFeign = essayFeign;
    }

    /**
     * 查找所有随笔
     */
    @GetMapping(value = "")
    public  Result  essay() {
        Object essay = essayFeign.essay();
        if (essay == null) {
            return new Result(false, StatusCode.DATA_NOT_FOUND, "查询失败");
        } else {
            return new Result(true, StatusCode.SUCCESS, "查询成功", essay);
        }

    }


    /**
     * 根据时间查找随笔
     */
    @GetMapping(value = "/date/{date}")
    public  Result  findByLGDate(@PathVariable String date) {
        Object essay = essayFeign.findByLGDate(date);
        if (essay == null) {
            return new Result(false, StatusCode.DATA_NOT_FOUND, "查询失败");
        } else {
            return new Result(true, StatusCode.SUCCESS, "查询成功", essay);
        }
    }

    /**
     * 添加随笔
     */
    @PostMapping(value = "")
    public Result saveEssay(@RequestBody Object essay) {
        boolean create = essayFeign.saveEssay(essay);
        if (create) {
            return new Result(false, StatusCode.SYSTEM_INNER_ERROR, "添加失败");
        } else {
            return new Result(true, StatusCode.SUCCESS, "添加成功");
        }
    }


    /**
     * 删除随笔
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteEssay(@PathVariable String id) {
        boolean delete = essayFeign.deleteEssay(id);
        if (delete) {
            return new Result(true, StatusCode.SUCCESS, "删除成功");
        } else {
            return new Result(false, StatusCode.DATA_NOT_FOUND, "删除失败");
        }
    }


}
