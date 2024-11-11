package com.example.spring_timesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringTimeSheetPageApplication {

    public static void main(String[] args) {
    SpringApplication.run(SpringTimeSheetPageApplication.class, args);
    }
}
