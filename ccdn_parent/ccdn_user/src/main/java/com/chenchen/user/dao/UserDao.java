package com.chenchen.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.chenchen.user.pojo.User;
/**
 * 用户管理Mapper
 * @author chenchen
 */
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{
	User findByMobile(String mobile);
}
