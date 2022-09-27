package org.novastart.infrastructure.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.novastart.infrastructure.domain.entitty.User;

public interface UserMapper extends BaseMapper<User> {
    default User findByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, username);
        return selectOne(wrapper);
    }
}