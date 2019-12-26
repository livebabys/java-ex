package com.bb.bb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.bb.bb.mapper")
@SpringBootApplication
public class BbApplication {

    public static void main(String[] args) {
        SpringApplication.run(BbApplication.class, args);
    }

}
