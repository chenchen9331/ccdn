package com.chenchen.friend.dao;

import com.chenchen.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 非好友
 * @author
 */
public interface NoFriendDao extends JpaRepository<NoFriend, String> {
    /**
     * 是否已经存在非好友关系
     * @return
     */
    NoFriend findByUseridAndFriendid(String userid, String friendid);
}
