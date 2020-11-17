package com.chenchen.article.controller;
import java.util.List;
import java.util.Map;

import com.chenchen.common.entity.PageResultEntity;
import com.chenchen.common.entity.ResultEntity;
import com.chenchen.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chenchen.article.pojo.Article;
import com.chenchen.article.service.ArticleService;

import javax.xml.stream.events.StartDocument;

/**
 * 文章Controller
 * @author chenchen
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public ResultEntity findAll(){
		return new ResultEntity(StatusCode.OK,true,"查询成功",articleService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public ResultEntity findById(@PathVariable String id){
		return new ResultEntity(StatusCode.OK,true,"查询成功",articleService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public ResultEntity findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<Article> pageList = articleService.findSearch(searchMap, page, size);
		return  new ResultEntity(StatusCode.OK,true, "查询成功", new PageResultEntity<Article>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public ResultEntity findSearch( @RequestBody Map searchMap){
        return new ResultEntity(StatusCode.OK,true,"查询成功",articleService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param article
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResultEntity add(@RequestBody Article article  ){
		articleService.add(article);
		return new ResultEntity(StatusCode.OK,true,"增加成功");
	}
	
	/**
	 * 修改
	 * @param article
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public ResultEntity update(@RequestBody Article article, @PathVariable String id ){
		article.setId(id);
		articleService.update(article);		
		return new ResultEntity(StatusCode.OK,true,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public ResultEntity delete(@PathVariable String id ){
		articleService.deleteById(id);
		return new ResultEntity(StatusCode.OK,true,"删除成功");
	}

	/**
	 * 文章审核
	 * @param articleid
	 * @return
	 */
	@RequestMapping(value = "/examine/{articleid}", method = RequestMethod.PUT)
	public ResultEntity examine(@PathVariable("articleid") String articleid) {
        articleService.updateState(articleid);
        return new ResultEntity(StatusCode.OK, true, "审核成功");
	}

	/**
	 * 文章点赞
	 * @param articleid
	 * @return
	 */
	@RequestMapping(value = "/thumbup/{articleid}", method = RequestMethod.PUT)
	public ResultEntity thumbup(@PathVariable("articleid") String articleid) {
		articleService.updateThumbup(articleid);
		return new ResultEntity(StatusCode.OK, true, "点赞成功");
	}
	
}
