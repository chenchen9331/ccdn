package com.chenchen.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.chenchen.user.pojo.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 用户管理Mapper
 * @author chenchen
 */
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{
	User findByMobile(String mobile);

    /**
     * 粉丝数更新
     * @param friendid
     */
	@Modifying
	@Query(value = "UPDATE tb_user SET fanscount = fanscount + ? WHERE id = ?", nativeQuery = true)
    void updateFans(int x, String friendid);

    /**
     * 关注数更新
     * @param userid
     */
    @Modifying
	@Query(value = "UPDATE tb_user SET followcount = followcount + ? WHERE id = ?", nativeQuery = true)
    void updateFollow(int x, String userid);
}
