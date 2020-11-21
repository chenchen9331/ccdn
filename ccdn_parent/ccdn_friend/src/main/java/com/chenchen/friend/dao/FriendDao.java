package com.chenchen.friend.dao;

import com.chenchen.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 交友Mapper
 * @author chenchen
 */
public interface FriendDao extends JpaRepository<Friend, String> {

    /**
     * 查询是否重复添加
     * @return
     */
    Friend findByUseridAndFriendid(String userid, String friendid);

    /**
     * 互相喜欢islike更新为1
     * @param islike
     * @param userid
     * @param friendid
     */
    @Modifying
    @Query(value = "UPDATE tb_friend SET islike = ? WHERE userid = ? AND friendid = ?", nativeQuery = true)
    void updateIsLike(String islike, String userid, String friendid);

    /**
     * 删除好友
     * @param userid
     * @param friendid
     */
    void deleteFriendByUseridAndFriendid(String userid, String friendid);
}
