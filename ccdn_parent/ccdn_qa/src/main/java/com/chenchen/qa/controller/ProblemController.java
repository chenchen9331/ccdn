package com.chenchen.qa.controller;
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

import com.chenchen.qa.pojo.Problem;
import com.chenchen.qa.service.ProblemService;

/**
 * 问题Controller
 * @author chenchen
 */
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

	@Autowired
	private ProblemService problemService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public ResultEntity findAll(){
		return new ResultEntity(StatusCode.OK,true,"查询成功",problemService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public ResultEntity findById(@PathVariable String id){
		return new ResultEntity(StatusCode.OK,true,"查询成功",problemService.findById(id));
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
		Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
		return  new ResultEntity(StatusCode.OK,true,"查询成功",  new PageResultEntity<Problem>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public ResultEntity findSearch( @RequestBody Map searchMap){
        return new ResultEntity(StatusCode.OK,true,"查询成功",problemService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param problem
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResultEntity add(@RequestBody Problem problem  ){
		problemService.add(problem);
		return new ResultEntity(StatusCode.OK,true,"增加成功");
	}
	
	/**
	 * 修改
	 * @param problem
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public ResultEntity update(@RequestBody Problem problem, @PathVariable String id ){
		problem.setId(id);
		problemService.update(problem);		
		return new ResultEntity(StatusCode.OK,true,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public ResultEntity delete(@PathVariable String id ){
		problemService.deleteById(id);
		return new ResultEntity(StatusCode.OK,true,"删除成功");
	}

	/**
	 * 最热回复
	 * @param labelid
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/newlist/{label}/{page}/{size}", method = RequestMethod.GET)
	public ResultEntity newList(@PathVariable("label") String labelid, @PathVariable("page") int page, @PathVariable("size") int size) {
		Page<Problem> pageList = problemService.newList(labelid, page, size);
		return new ResultEntity(StatusCode.OK, true, "查询成功", new PageResultEntity<Problem>(pageList.getTotalElements(), pageList.getContent()));
	}

	/**
	 * 最热回复
	 * @param labelid
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/hotlist/{label}/{page}/{size}", method = RequestMethod.GET)
	public ResultEntity hotList(@PathVariable("label") String labelid, @PathVariable("page") int page, @PathVariable("size") int size) {
		Page<Problem> pageList = problemService.hotList(labelid, page, size);
		return new ResultEntity(StatusCode.OK, true, "查询成功", new PageResultEntity<Problem>(pageList.getTotalElements(), pageList.getContent()));
	}

	/**
	 * 等待回复
	 * @param labelid
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/waitlist/{label}/{page}/{size}", method = RequestMethod.GET)
	public ResultEntity waitList(@PathVariable("label") String labelid, @PathVariable("page") int page, @PathVariable("size") int size) {
		Page<Problem> pageList = problemService.waitList(labelid, page, size);
		return new ResultEntity(StatusCode.OK, true, "查询成功", new PageResultEntity<Problem>(pageList.getTotalElements(), pageList.getContent()));
	}
}
