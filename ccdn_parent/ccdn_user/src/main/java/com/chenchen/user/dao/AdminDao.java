package com.chenchen.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.chenchen.user.pojo.Admin;
/**
 * 管理员Mapper
 * @author chenchen
 */
public interface AdminDao extends JpaRepository<Admin,String>,JpaSpecificationExecutor<Admin>{
	
}
