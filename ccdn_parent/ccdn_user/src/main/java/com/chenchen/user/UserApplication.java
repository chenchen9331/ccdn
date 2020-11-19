package com.chenchen.user;
import com.chenchen.common.util.IdWorker;
import com.chenchen.common.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 用户管理启动类
 * @author chenchen
 */
@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	/**
	 * id生成器（雪花算法）
	 * @return
	 */
	@Bean
	public IdWorker idWorkker(){
		return new IdWorker(1, 1);
	}

	/**
	 * 密码盐值加密
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * jwt生成token
	 * @return
	 */
	@Bean
	public JwtUtil jwtUtil() {
		return new JwtUtil();
	}
}
