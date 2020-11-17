package com.chenchen.base.service;

import com.chenchen.base.dao.LabelDao;
import com.chenchen.base.pojo.Label;
import com.chenchen.common.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 标签Service
 * @author chenchen
 */
@Service
@Transactional
public class LabelService {

    /**
     * 注入LabelDao
     */
    @Autowired
    private LabelDao labelDao;

    /**
     * 注入IdWorker
     */
    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部标签
     * @return
     */
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    /**
     * 根据Id查询标签
     * @param labelId
     * @return
     */
    public Label findLabelById(String labelId) {
        return labelDao.findById(labelId).get();
    }

    /**
     * 更新标签
     * @param label
     */
    public void update(Label label) {
        labelDao.save(label);
    }

    /**
     * 保存标签
     * @param label
     */
    public void save(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    /**
     * 删除标签
     * @param labelId
     */
    public void delete(String labelId) {
        labelDao.deleteById(labelId);
    }

    /**
     * 根据查询条件搜索
     * @param label
     * @return
     */
    public List<Label> findBySearch(Label label) {
         return labelDao.findAll(new Specification<Label>() {
            /**
             * SpringData JPA 根据搜索条件查询
             * @param root 封装查询条件
             * @param query 查询关键字
             * @param criteriaBuilder 封装条件对象
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // 封装查询条件
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 标签名称
                if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    predicateList.add(predicate);
                }
                // 状态
                if (label.getState() != null && !"".equals(label.getState())) {
                    Predicate predicate = criteriaBuilder.like(root.get("state").as(String.class), "%" + label.getState() + "%");
                    predicateList.add(predicate);
                }
                // 是否推荐
                if (label.getRecommend() != null && !"".equals(label.getRecommend())) {
                    Predicate predicate = criteriaBuilder.like(root.get("recommend").as(String.class), "%" + label.getRecommend() + "%");
                    predicateList.add(predicate);
                }

                // 查询条件List转换成数组
                Predicate[] preArray = new Predicate[predicateList.size()];
                predicateList.toArray(preArray);
                return criteriaBuilder.and(preArray);
            }
        });
    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        List<Predicate> predicateList = new ArrayList<Predicate>();
        return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // 封装查询条件
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 标签名称
                if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    predicateList.add(predicate);
                }
                // 状态
                if (label.getState() != null && !"".equals(label.getState())) {
                    Predicate predicate = criteriaBuilder.like(root.get("state").as(String.class), "%" + label.getState() + "%");
                    predicateList.add(predicate);
                }
                // 是否推荐
                if (label.getRecommend() != null && !"".equals(label.getRecommend())) {
                    Predicate predicate = criteriaBuilder.like(root.get("recommend").as(String.class), "%" + label.getRecommend() + "%");
                    predicateList.add(predicate);
                }

                // 查询条件List转换成数组
                Predicate[] preArray = new Predicate[predicateList.size()];
                predicateList.toArray(preArray);
                return criteriaBuilder.and(preArray);
            }
        }, pageable);
    }
}
