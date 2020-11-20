package com.chenchen.qa.client;

import com.chenchen.common.entity.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("ccdn-base")
public interface BaseClient {

    /**
     * 根据Id查询标签
     * @param labelId
     * @return
     */
    @RequestMapping(value = "/label/{labelId}", method = RequestMethod.GET)
    public ResultEntity findLabelById(@PathVariable("labelId") String labelId);
}
