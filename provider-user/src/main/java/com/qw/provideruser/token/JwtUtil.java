package com.qw.provideruser.token;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import com.qw.provideruser.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import lombok.Data;

import javax.crypto.spec.SecretKeySpec;
import java.text.SimpleDateFormat;
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
    private long TTL;


    public static String jwtCreateToken(User user) {
        long now = System.currentTimeMillis();
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        String jws = Jwts.builder()
                .setId(user.getId())
                .setIssuer("qw")
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
            //    .setExpiration(new Date(now + 60000000))
                .claim("role",user.getRole())
                //.signWith()
                .compact();

        System.out.println(jws);
        return jws;
    }

    public static Claims jwtParseToken(String token) {
        try {
            return Jwts
                    .parserBuilder()
                  //  .setSigningKey()
                    .build()
                    .parseClaimsJwt(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            System.out.println("过期");
        }
        return null;

    }
}