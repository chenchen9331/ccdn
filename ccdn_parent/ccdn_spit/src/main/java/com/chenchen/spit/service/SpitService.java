package com.chenchen.spit.service;

import com.chenchen.common.util.IdWorker;
import com.chenchen.spit.dao.SpitDao;
import com.chenchen.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 吐槽评论Service
 * @author chenchen
 */
@Service
@Transactional
public class SpitService {

    /**
     * 注入spitDao
     */
    @Autowired
    private SpitDao spitDao;

    /**
     * 注入IdWorker
     */
    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查询所有
     * @return
     */
    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Spit findById(String id) {
        return spitDao.findById(id).get();
    }

    /**
     * 保存
     * @param spit
     */
    public void save(Spit spit) {
        spit.set_id(idWorker.nextId() + "");
        spit.setPublishtime(new Date());//发布日期
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态
        if (spit.getParentid() != null && !"".equals(spit.getParentid())) {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment", 1);
            mongoTemplate.updateFirst(query, update, "spit");
        }
        spitDao.save(spit);
    }

    /**
     * 更新
     * @param spit
     */
    public void update(Spit spit) {
        spitDao.save(spit);
    }

    /**
     * 根据id删除
     * @param id
     */
    public void delete(String id) {
        spitDao.deleteById(id);
    }

    public Page<Spit> findByParentid(String parrentId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Spit> byParentid = spitDao.findByParentid(parrentId, pageable);
        return byParentid;
    }

    public void thumbup(String spitId) {
        // Spit spit = spitDao.findById(spitId).get();
        // spit.setThumbup((spit.getThumbup() == null ? 0 : spit.getThumbup()) + 1);
        // spitDao.save(spit);

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        Update update = new Update();
        update.inc("thumbup", 1);
        mongoTemplate.updateMulti(query, update, "spit");
    }
}
