package org.novastart.blog.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.novastart.infrastructure.utils.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(tags = "首页模块")
public class TestController {

    @GetMapping("/admin/test")
    public  Response test(){
        return Response.success();
    }

}
