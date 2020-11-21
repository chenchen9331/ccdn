package com.chenchen.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("ccdn-user")
public interface UserClient {

    @RequestMapping(value = "/user/{userid}/{friendid}/{x}", method = RequestMethod.PUT)
    void updateFansAndFollow(@PathVariable("x") int x, @PathVariable("userid") String userid, @PathVariable("friendid") String friendid);
}
