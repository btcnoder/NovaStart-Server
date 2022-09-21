package org.novastart.admin;

import static org.novastart.infrastructure.utils.JwtTokenHelper.generateBase64Key;

import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.novastart.infrastructure.domain.dos.UserDO;
import org.novastart.infrastructure.domain.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class NovaStartAdminApplicationTests {

    @Test
    void contextLoads() {
    }

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
        UserDO userDO = UserDO.builder()
            .username("犬小哈")
            .password("123456")
            .createTime(new Date())
            .updateTime(new Date())
            .isDeleted(false)
            .build();

        userMapper.insert(userDO);
    }

    @Test
    void jwtTest(){
        String key = generateBase64Key();
        System.out.println("key: " + key);

    }
}
