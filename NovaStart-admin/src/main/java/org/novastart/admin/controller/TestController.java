package org.novastart.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.novastart.admin.model.User;
import org.novastart.infrastructure.aspect.ApiOperationLog;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @PostMapping("/test")
    @ApiOperationLog(description = "测试接口")
    public User test(@RequestBody User user) {
        // 返参
        return user;
    }

}
