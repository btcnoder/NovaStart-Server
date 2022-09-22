package org.novastart.infrastructure.security;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.novastart.infrastructure.exception.error.ErrorCodeEnum;
import org.novastart.infrastructure.utils.Response;
import org.novastart.infrastructure.utils.ResultUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        log.warn("AuthenticationException: ", exception);

        // 登录失败
        ResultUtil.fail(response, Response.fail(ErrorCodeEnum.LOGIN_FAIL));
    }
}
