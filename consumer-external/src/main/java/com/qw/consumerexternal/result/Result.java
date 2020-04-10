package com.qw.consumerexternal.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author Eclair
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class Result {
    private boolean flag;
    private String message;
    private Object data;

    public Result(boolean flag,  String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public Result(boolean flag,  String message) {
        this.flag = flag;
        this.message = message;
    }
}
