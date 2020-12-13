package com.bigstomach.tongjihealthcare;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.bigstomach")
@SpringBootApplication
@MapperScan("com.bigstomach.tongjihealthcare.mapper")
public class TongjiHealthCareApplication {

    public static void main(String[] args) {
        SpringApplication.run(TongjiHealthCareApplication.class, args);
    }

}
