package com.qw.provideruser.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


/**
 * @Program: cloud-blog
 * @ClassName User
 * @Description:
 * @Author: Eclair
 * @Create: 2019-12-31 15:43
 * @Version 1.0
 **/

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String nickname;
    private String role;
    private String type;
    private String avatar;
    private String date;
}
