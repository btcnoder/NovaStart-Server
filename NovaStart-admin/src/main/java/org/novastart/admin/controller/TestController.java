package org.novastart.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.novastart.admin.model.User;
import org.novastart.admin.model.User2;
import org.novastart.infrastructure.aspect.ApiOperationLog;
import org.novastart.infrastructure.utils.JsonUtil;
import org.novastart.infrastructure.utils.Response;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(tags = "首页模块")
public class TestController {

    @PostMapping("/test")
    @ApiOperationLog(description = "测试接口")
    @ApiOperation(value = "测试接口")
    public Response test(@RequestBody @Validated User user) {
        // 打印入参
        log.info(JsonUtil.toJsonString(user));

        // 设置三种日期字段值
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateDate(LocalDate.now());
        user.setTime(LocalTime.now());

        return Response.success(user);
    }

    @GetMapping("/admin/test2")
    public  Response test2(){
        return Response.success();
    }


    // 注意：这里@Autowired是报错的，因为@Autowired按照类名注入的
    @Resource
    private RedisTemplate<String, String> redisTemplate;



    @ApiOperation("Add")
    @PostMapping("/test/add")
    public Response<String> add(User2 user2) {
        redisTemplate.opsForValue().set(String.valueOf(user2.getId()), user2.toString());
        return Response.success(redisTemplate.opsForValue().get(String.valueOf(user2.getId())));
    }

}
