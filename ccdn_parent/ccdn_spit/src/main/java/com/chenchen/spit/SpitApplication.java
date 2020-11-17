package com.chenchen.spit;

import com.chenchen.common.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 吐槽评论微服务启动类
 * @author chenchen
 */
@SpringBootApplication
public class SpitApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpitApplication.class, args);
    }

    /**
     * id生成器（雪花算法）
     * @return
     */
    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
}
