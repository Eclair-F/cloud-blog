package com.qw.provideressay.controller;


import com.qw.provideressay.entity.Essay;
import com.qw.provideressay.entity.PageResult;
import com.qw.provideressay.services.CreateService;
import com.qw.provideressay.services.DeleteService;
import com.qw.provideressay.services.RetrieveService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Eclair
 */
@RestController
public class EssayController {
    private final CreateService createService;
    private final RetrieveService retrieveService;
    private final DeleteService deleteService;
    private final HttpServletRequest request;
    private final String Admin = "admin";
    private String Role;

    public EssayController(RetrieveService retrieveService, CreateService createService, DeleteService deleteService, HttpServletRequest request) {
        this.retrieveService = retrieveService;
        this.createService = createService;
        this.deleteService = deleteService;
        this.request = request;
    }


    private String getRole() {
        return (String) request.getAttribute("role");
    }

    private PageResult<Essay> essayPageResult(PageResult<Essay> pageResult) {
        if (pageResult.getRows().isEmpty()) {
            return null;
        } else {
            return pageResult;
        }
    }

    /**
     * 查找所有随笔
     */
    @GetMapping(value = "/")
    public PageResult<Essay> essay() {
            return essayPageResult(retrieveService.findAllEssay());
    }


    /**
     * 根据时间查找随笔
     */
    @GetMapping(value = "/date/{date}")
    public PageResult<Essay> findByLGDate(@PathVariable String date) {
            String reg = "^\\d{4}-\\d{4}$";
            if (date.matches(reg)) {
                String[] temp = date.split("-");
                return essayPageResult(retrieveService.findByDate(temp[0], temp[1]));
            } else {

                return essayPageResult(retrieveService.findByDate(date));
            }
    }

    /**
     * 添加随笔
     */
    @PostMapping(value = "/")
    public boolean saveEssay(@RequestBody Essay essay) {
        if (Admin.equals(getRole())) {
            return createService.createEssay(essay);
        }
        return false;

    }


    /**
     * 删除随笔
     */
    @DeleteMapping(value = "/{id}")
    public boolean deleteEssay(@PathVariable String id) {
        if (Admin.equals(getRole())) {
            return deleteService.deleteById(id);
        }
        return false;

    }


}
