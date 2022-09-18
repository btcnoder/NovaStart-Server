package org.novastart.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.novastart.admin.model.User;
import org.novastart.infrastructure.aspect.ApiOperationLog;
import org.novastart.infrastructure.enums.ResponseCodeEnum;
import org.novastart.infrastructure.exception.BizException;
import org.novastart.infrastructure.utils.Response;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @PostMapping("/test")
    @ApiOperationLog(description = "测试接口")
    public Response test(@RequestBody @Validated User user, BindingResult bindingResult) {
        // 手动抛异常，入参是前面定义好的异常码枚举，返参统一交给全局异常处理器搞定
        throw new BizException(ResponseCodeEnum.PRODUCT_NOT_FOUND);
    }

}
