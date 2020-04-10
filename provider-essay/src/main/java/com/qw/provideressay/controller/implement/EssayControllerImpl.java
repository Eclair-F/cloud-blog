package com.qw.provideressay.controller.implement;

import com.qw.provideressay.controller.EssayController;
import com.qw.provideressay.entity.Essay;
import com.qw.provideressay.entity.PageResult;
import com.qw.provideressay.services.EssayService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Program: cloud-blog
 * @ClassName EssayControllerImpl
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-27 23:34
 * @Version 1.0
 **/
@RestController
public class EssayControllerImpl implements EssayController {
    private final EssayService essayService;
    private final HttpServletRequest request;
    private final String Admin = "admin";


    public EssayControllerImpl(EssayService essayService, HttpServletRequest request) {
        this.essayService = essayService;
        this.request = request;
    }


    private String getRole() {
        return (String) request.getAttribute("role");
    }


    /**
     * 查找所有随笔
     */
    @Override
    @GetMapping(value = "/")
    public PageResult<Essay> essay() {
        return essayService.findAllEssay();
    }


    /**
     * 添加随笔
     */
    @Override
    @PostMapping(value = "/")
    public boolean saveEssay(@RequestBody Essay essay) {
        if (Admin.equals(getRole())) {
            return essayService.createEssay(essay);
        }
        return false;

    }


    /**
     * 删除随笔
     */
    @Override
    @DeleteMapping(value = "/{id}")
    public boolean deleteEssay(@PathVariable String id) {
        if (Admin.equals(getRole())) {
            return essayService.deleteById(id);
        }
        return false;
    }

}
