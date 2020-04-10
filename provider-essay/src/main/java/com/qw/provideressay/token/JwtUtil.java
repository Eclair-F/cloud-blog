package com.qw.provideressay.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

/**
 * @Program: cloud-blog
 * @ClassName JwtUtil
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-04 15:54
 * @Version 1.0
 **/

public class JwtUtil {
    public static Claims jwtParseToken(String token) {
        try {
            return Jwts
                    .parserBuilder()
                    .build()
                    .parseClaimsJwt(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return null;
        }
    }
}