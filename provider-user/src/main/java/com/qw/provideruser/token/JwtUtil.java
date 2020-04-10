package com.qw.provideruser.token;

import com.qw.provideruser.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.Data;

import java.util.Date;

/**
 * @Program: cloud-blog
 * @ClassName JwtUtil
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-04 15:54
 * @Version 1.0
 **/
@Data
public class JwtUtil {

    public static String jwtCreateToken(User user) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setId(user.getId())
                .setIssuer("qw")
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(now + 259200000))
                .claim("role", user.getRole())
                .claim("password", user.getPassword())
                .compact();
    }

    public static Claims jwtParseToken(String token) {
        try {
            return Jwts
                    .parserBuilder()
                    .build()
                    .parseClaimsJwt(token)
                    .getBody();
        } catch (ExpiredJwtException ignored) {
            return null;
        }


    }
}