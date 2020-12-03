package com.bigstomach.tongjihealthcare;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bigstomach.tongjihealthcare.mapper")
public class TongjiHealthCareApplication {

    public static void main(String[] args) {
        SpringApplication.run(TongjiHealthCareApplication.class, args);
    }

}
