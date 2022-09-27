package org.novastart.infrastructure.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.novastart.infrastructure.domain.entitty.LoginUser;
import org.novastart.infrastructure.domain.vo.LoginRspVO;
import org.novastart.infrastructure.utils.JwtTokenHelper;
import org.novastart.infrastructure.utils.RedisUtil;
import org.novastart.infrastructure.utils.Response;
import org.novastart.infrastructure.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 从 authentication 对象中获取用户的 UserDetails 实例，这里是获取用户的用户名
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        // 通过用户名生成 Token
        String userId = loginUser.getUser().getId().toString();
        String token = jwtTokenHelper.generateToken(userId);

        //userDetails存入redis
        redisUtil.set("LoginIdPrefix:" + userId,loginUser);

        // 返回 Token
        LoginRspVO loginRspVO = LoginRspVO.builder().token(token).build();
        ResultUtil.ok(response, Response.success(loginRspVO));
    }
}