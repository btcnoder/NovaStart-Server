package org.novastart.infrastructure.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.novastart.infrastructure.domain.mapper")
public class MybatisPlusConfig {
}