package com.chenchen.spit.controller;

import com.chenchen.common.entity.PageResultEntity;
import com.chenchen.common.entity.ResultEntity;
import com.chenchen.common.entity.StatusCode;
import com.chenchen.spit.pojo.Spit;
import com.chenchen.spit.service.SpitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * 吐槽评论Controller
 * @author chenchen
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    SpitService spitService;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 查询全部
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResultEntity findAll() {
        return new ResultEntity(StatusCode.OK, true, "查询成功", spitService.findAll());
    }

    /**
     * 单条查询
     * @param spitid
     * @return
     */
    @RequestMapping(value = "/{spitid}", method = RequestMethod.GET)
    public ResultEntity findById(@PathVariable("spitid") String spitid) {
        return new ResultEntity(StatusCode.OK, true, "查询成功", spitService.findById(spitid));
    }

    /**
     * 保存
     * @param spit
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultEntity save(@RequestBody Spit spit) {
        spitService.save(spit);
        return new ResultEntity(StatusCode.OK, true, "保存成功");
    }

    /**
     * 修改
     * @param spit
     * @return
     */
    @RequestMapping(value = "/{spitid}", method = RequestMethod.PUT)
    public ResultEntity update(@PathVariable("spitid") String spitid, @RequestBody Spit spit) {
        spit.set_id(spitid);
        spitService.update(spit);
        return new ResultEntity(StatusCode.OK, true, "修改成功");
    }

    /**
     * 删除
     * @param spitid
     * @return
     */
    @RequestMapping(value = "/{spitid}", method = RequestMethod.DELETE)
    public ResultEntity delete(@PathVariable("spitid") String spitid) {
        spitService.delete(spitid);
        return new ResultEntity(StatusCode.OK, true, "删除成功");
    }

    @RequestMapping(value = "/comment/{parentid}/{page}/{size}", method = RequestMethod.GET)
    public ResultEntity findByParrentId(@PathVariable("parentid") String parentid, @PathVariable("page") int page, @PathVariable("size") int size) {
        Page<Spit> pagedate = spitService.findByParentid(parentid, page, size);
        return new ResultEntity(StatusCode.OK, true, "查询成功", new PageResultEntity<Spit>(pagedate.getTotalElements(), pagedate.getContent()));
    }

    @RequestMapping(value = "/thumbup/{spitId}", method = RequestMethod.GET)
    public ResultEntity thumbup(@PathVariable("spitId") String spitId) {
        String userid = "111";
        if (redisTemplate.opsForValue().get("thumbup_" + userid) != null) {
            return new ResultEntity(StatusCode.REPERROR, false, "不能重复点赞");
        }
        spitService.thumbup(spitId);
        redisTemplate.opsForValue().set("thumbup_" + userid, 1);
        return new ResultEntity(StatusCode.OK, true, "点赞成功");
    }
}
