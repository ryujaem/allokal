package com.spring.app.allokal.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.spring.app.allokal.mapper")
@Configuration
public class DatasourceConfig {

}
