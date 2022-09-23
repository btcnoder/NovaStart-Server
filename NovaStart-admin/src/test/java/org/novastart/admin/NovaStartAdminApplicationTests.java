package org.novastart.admin;

import static org.novastart.infrastructure.utils.JwtTokenHelper.generateBase64Key;

import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.novastart.admin.model.User3;
import org.novastart.infrastructure.domain.dos.User;
import org.novastart.infrastructure.domain.mapper.UserMapper;
import org.novastart.infrastructure.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
@Slf4j
class NovaStartAdminApplicationTests {

    @Test
    void testLog() {
        log.info("这是一行 Info 级别日志");
        log.warn("这是一行 Warn 级别日志");
        log.error("这是一行 Error 级别日志");

        // 占位符
        String author = "犬小哈";
        log.info("这是一行带有占位符日志，作者：{}", author);
    }


    @Autowired
    private UserMapper userMapper;

    @Test
    void insertTest() {
        // 构建数据库实体类
        User user = User.builder()
            .username("犬小哈")
            .password("123456")
            .createTime(new Date())
            .updateTime(new Date())
            .isDeleted(false)
            .build();

        userMapper.insert(user);
    }

    @Test
    void jwtTest(){
        String key = generateBase64Key();
        System.out.println("key: " + key);

    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;
    @Test
    public void test(){
        //真实开发中都使用json来传递对象
        User3 user = new User3();
        user.setName("笨笨熊2");
        user.setAge(4);
        redisUtil.set("user",user);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }
}
