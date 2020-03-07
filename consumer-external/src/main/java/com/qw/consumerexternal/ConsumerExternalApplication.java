package com.qw.consumerexternal;

import com.qw.consumerexternal.token.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @author Eclair
 */
@SpringBootApplication
@EnableFeignClients
public class ConsumerExternalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerExternalApplication.class, args);
    }


}
