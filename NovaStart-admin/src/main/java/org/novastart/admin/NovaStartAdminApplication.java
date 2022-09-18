package org.novastart.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"org.novastart.*"})
public class NovaStartAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(NovaStartAdminApplication.class, args);
    }

}
