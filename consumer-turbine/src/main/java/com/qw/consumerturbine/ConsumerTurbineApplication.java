package com.qw.consumerturbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author Eclair
 */
@SpringBootApplication
@EnableTurbine
@EnableHystrixDashboard
public class ConsumerTurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerTurbineApplication.class, args);
    }

}
