package com.chenchen.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.chenchen.recruit.pojo.Enterprise;

import java.util.List;

/**
 * 企业Mapper
 * @author chenchen
 */
public interface EnterpriseDao extends JpaRepository<Enterprise,String>,JpaSpecificationExecutor<Enterprise>{

    /**
     * 查找热门企业
     * @param ishot
     * @return
     */
	List<Enterprise> findByIshot(String ishot);
}
