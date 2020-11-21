package com.chenchen.friend.controller;

import com.chenchen.common.entity.ResultEntity;
import com.chenchen.common.entity.StatusCode;
import com.chenchen.common.util.JwtUtil;
import com.chenchen.friend.service.FriendService;
import io.jsonwebtoken.Claims;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 交友Controller
 * @author chenchen
 */
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private FriendService friendService;

    /**
     * 添加好友/添加非好友
     * @return
     */
    @RequestMapping(value = "/like/{friendid}/{type}", method = RequestMethod.GET)
    public ResultEntity addFriend(@PathVariable("friendid") String friendid, @PathVariable("type") String type) {
        // 验证登录
        Claims claims = (Claims) request.getAttribute("claims_user");
        if (claims == null) {
            return new ResultEntity(StatusCode.LOGINERROR, false, "没有权限");
        }
        String userid = claims.getId();
        // 添加好友还是非好友
        if (type != null) {
            if ("1".equals(type)) {
                // 添加好友（相当于关注）
                int flag = friendService.addFriend(userid, friendid);
                if (flag == 0) {
                    return new ResultEntity(StatusCode.ERROR, false, "不能重复添加");
                }
                if (flag == 1) {
                    return new ResultEntity(StatusCode.OK, true, "添加成功");
                }
            } else if ("2".equals(type)) {
                // 添加非好友（相当于不感兴趣）
                int flag = friendService.addUnFriend(userid, friendid);
                if (flag == 0) {
                    return new ResultEntity(StatusCode.ERROR, false, "不能重复添加");
                }
                if (flag == 1) {
                    return new ResultEntity(StatusCode.OK, true, "添加成功");
                }
            }
            return new ResultEntity(StatusCode.ERROR, false, "参数错误");
        }
        return null;
    }

    @RequestMapping(value = "/{friendid}", method = RequestMethod.DELETE)
    public ResultEntity deleteFriend(@PathVariable("friendid") String friendid) {
        Claims claims = (Claims) request.getAttribute("claims_user");
        if (claims == null) {
            return new ResultEntity(StatusCode.LOGINERROR, false, "请先登陆");
        }
        String userid = claims.getId();
        friendService.deleteFriend(userid, friendid);
        return new ResultEntity(StatusCode.OK, true, "删除成功");
    }
}
