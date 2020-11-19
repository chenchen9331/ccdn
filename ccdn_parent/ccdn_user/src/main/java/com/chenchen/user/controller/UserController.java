package com.chenchen.user.controller;
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

import com.chenchen.user.pojo.User;
import com.chenchen.user.service.UserService;

/**
 * 用户管理Controller
 * @author chenchen
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 注册发送短信验证码
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "/sendsms/{mobile}", method = RequestMethod.GET)
	public ResultEntity sendSms(@PathVariable("mobile") String mobile) {
        userService.sendSms(mobile);
        return new ResultEntity(StatusCode.OK, true, "发送成功");
	}

	/**
	 * 用户注册
	 * @param code
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/register/{code}", method = RequestMethod.POST)
	public ResultEntity register(@PathVariable("code") String code, @RequestBody User user) {
        userService.regist(code, user);
        return new ResultEntity(StatusCode.OK, true, "注册成功");
	}

	/**
	 * 用户登陆
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResultEntity login(@RequestBody User user) {
		User userLogin = userService.login(user.getPassword(), user.getMobile());
		if (userLogin == null) {
			return new ResultEntity(StatusCode.LOGINERROR, false, "登陆失败");
		}
		return new ResultEntity(StatusCode.OK, true, "登陆成功");
	}

	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public ResultEntity findAll(){
		return new ResultEntity(StatusCode.OK,true,"查询成功",userService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public ResultEntity findById(@PathVariable String id){
		return new ResultEntity(StatusCode.OK,true,"查询成功",userService.findById(id));
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
		Page<User> pageList = userService.findSearch(searchMap, page, size);
		return  new ResultEntity(StatusCode.OK,true,"查询成功",  new PageResultEntity<User>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public ResultEntity findSearch( @RequestBody Map searchMap){
        return new ResultEntity(StatusCode.OK,true,"查询成功",userService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param user
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResultEntity add(@RequestBody User user  ){
		userService.add(user);
		return new ResultEntity(StatusCode.OK,true,"增加成功");
	}
	
	/**
	 * 修改
	 * @param user
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public ResultEntity update(@RequestBody User user, @PathVariable String id ){
		user.setId(id);
		userService.update(user);		
		return new ResultEntity(StatusCode.OK,true,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public ResultEntity delete(@PathVariable String id ){
		userService.deleteById(id);
		return new ResultEntity(StatusCode.OK,true,"删除成功");
	}
	
}
