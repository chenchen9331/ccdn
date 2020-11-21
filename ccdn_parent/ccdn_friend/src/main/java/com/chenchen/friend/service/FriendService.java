package com.chenchen.friend.service;

import com.chenchen.friend.client.UserClient;
import com.chenchen.friend.dao.FriendDao;
import com.chenchen.friend.dao.NoFriendDao;
import com.chenchen.friend.pojo.Friend;
import com.chenchen.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 交友Service
 * @author chenchen
 */
@Service
@Transactional
public class FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;

    @Autowired
    private UserClient userClient;

    /**
     * 添加好友
     * @param userid
     * @param friendid
     * @return
     */
    public int addFriend(String userid, String friendid) {

        // 查询是否重复添加
        Friend friend = friendDao.findByUseridAndFriendid(userid, friendid);
        if (friend != null) {
            return 0;
        }
        friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        if (friendDao.findByUseridAndFriendid(friendid, userid) != null) {
            friend.setIslike("1");
            friendDao.save(friend);
            friendDao.updateIsLike("1", friendid, userid);
        } else {
            friend.setIslike("0");
            friendDao.save(friend);
        }
        userClient.updateFansAndFollow(1, userid, friendid);
        return 1;
    }

    /**
     * 添加非好友（相当于不感兴趣）
     * @param userid
     * @param friendid
     * @return
     */
    public int addUnFriend(String userid, String friendid) {
        NoFriend noFriend = noFriendDao.findByUseridAndFriendid(userid, friendid);
        if (noFriend != null) {
            return 0;
        }
        noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
        return 1;
    }

    /**
     * 删除好友
     * @param userid
     * @param friendid
     * @return
     */
    public void deleteFriend(String userid, String friendid) {
        // 删除好友，islike置为0
        friendDao.deleteFriendByUseridAndFriendid(userid, friendid);
        friendDao.updateIsLike("0", friendid, userid);
        // 添加非好友
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
        // 更新关注数粉丝数
        userClient.updateFansAndFollow(-1, userid, friendid);
    }
}