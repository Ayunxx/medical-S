package com.ayun.medical.hosp.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.ayun.medical.hosp.dao")
public class HospConfig {
}
