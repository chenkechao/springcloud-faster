package com.csp.github.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author 陈少平
 * @date 2019-12-01 18:45
 */
@SpringBootApplication
@EnableZuulProxy
public class GetWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetWayApplication.class);
    }
}