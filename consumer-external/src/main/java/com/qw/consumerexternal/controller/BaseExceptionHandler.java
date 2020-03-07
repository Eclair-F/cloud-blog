package com.qw.consumerexternal.controller;

import com.qw.consumerexternal.result.Result;
import com.qw.consumerexternal.result.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

///**
// * @author Eclair
// */
//@RestControllerAdvice
//public class BaseExceptionHandler {
//    @ExceptionHandler(value = Exception.class)
//    public Result error(Exception e) {
//        return new Result(false, StatusCode.SYSTEM_INNER_ERROR, e.getMessage());
//    }
//}
