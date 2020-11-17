package com.chenchen.search.controller;

import com.chenchen.common.entity.PageResultEntity;
import com.chenchen.common.entity.ResultEntity;
import com.chenchen.common.entity.StatusCode;
import com.chenchen.search.pojo.Article;
import com.chenchen.search.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * 文章搜索Controller
 * @author chenchen
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    /**
     * 新增文章s索引
     * @param article
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultEntity save(@RequestBody Article article) {
        articleService.save(article);
        return new ResultEntity(StatusCode.OK, true, "新增成功");
    }

    /**
     * 根据关键字搜索
     * @param key
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/{key}/{page}/{size}", method = RequestMethod.GET)
    public ResultEntity findByKey(@PathVariable("key") String key, @PathVariable("page") int page, @PathVariable("size") int size) {
        Page<Article> pagedate = articleService.findByKey(key, page, size);
        return new ResultEntity(StatusCode.OK, true, "查询成功", new PageResultEntity<Article>(pagedate.getTotalElements(), pagedate.getContent()));
    }
}
