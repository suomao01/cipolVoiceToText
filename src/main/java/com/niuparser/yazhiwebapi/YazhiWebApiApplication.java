package com.niuparser.yazhiwebapi;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableScheduling
@MapperScan("com.niuparser.yazhiwebapi.dao")
public class YazhiWebApiApplication {

	protected static Logger logger = LoggerFactory.getLogger(YazhiWebApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(YazhiWebApiApplication.class, args);
	}
}
