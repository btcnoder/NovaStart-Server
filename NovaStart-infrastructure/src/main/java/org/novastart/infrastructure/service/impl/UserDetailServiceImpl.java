package org.novastart.infrastructure.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.novastart.infrastructure.domain.entitty.LoginUser;
import org.novastart.infrastructure.domain.entitty.User;
import org.novastart.infrastructure.exception.error.ErrorCodeEnum;
import org.novastart.infrastructure.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中查询
        User user = userMapper.findByUsername(username);

        // 判断用户是否存在
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(ErrorCodeEnum.USERNAME_NOT_EXIST.getErrorMessage());
        }

        List<String> permissions = new ArrayList<>(Arrays.asList("test","admin"));

        return new LoginUser(user,permissions);
    }
}