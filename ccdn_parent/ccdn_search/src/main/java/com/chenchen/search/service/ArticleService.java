package com.chenchen.search.service;

import com.chenchen.common.util.IdWorker;
import com.chenchen.search.dao.ArticleDao;
import com.chenchen.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    ArticleDao articleDao;

    //@Autowired
    //IdWorker idWorker;

    /**
     * 保存文章索引
     * @param article
     */
    public void save(Article article) {
        //article.setId(idWorker.nextId() + "");
        articleDao.save(article);
    }

    /**
     * 根据关键字搜索
     * @param key
     * @param page
     * @param size
     * @return
     */
    public Page<Article> findByKey(String key, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return articleDao.findByTitleOrContentLike(key, key, pageable);
    }
}
