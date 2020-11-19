package com.chenchen.user.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chenchen.common.entity.PageResultEntity;
import com.chenchen.common.entity.ResultEntity;
import com.chenchen.common.entity.StatusCode;
import com.chenchen.common.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chenchen.user.pojo.Admin;
import com.chenchen.user.service.AdminService;

/**
 * 管理员Controller
 * @author chenchen
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private JwtUtil jwtUtil;
	/**
	 * 管理员登陆
	 * @param admin
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResultEntity login(@RequestBody Admin admin) {
		Admin adminLogin = adminService.login(admin);
		if (adminLogin == null) {
			return new ResultEntity(StatusCode.LOGINERROR, false, "登陆失败");
		}
		// jwt生成token,角色暂时写死
		String token = jwtUtil.createJWT(adminLogin.getId(), adminLogin.getLoginname(), "admin");
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("name", adminLogin.getLoginname());
		return new ResultEntity(StatusCode.OK, true, "登陆成功", map);
	}
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public ResultEntity findAll(){
		return new ResultEntity(StatusCode.OK,true,"查询成功",adminService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public ResultEntity findById(@PathVariable String id){
		return new ResultEntity(StatusCode.OK,true,"查询成功",adminService.findById(id));
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
		Page<Admin> pageList = adminService.findSearch(searchMap, page, size);
		return  new ResultEntity(StatusCode.OK,true,"查询成功",  new PageResultEntity<Admin>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public ResultEntity findSearch( @RequestBody Map searchMap){
        return new ResultEntity(StatusCode.OK,true,"查询成功",adminService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param admin
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResultEntity add(@RequestBody Admin admin  ){
		adminService.add(admin);
		return new ResultEntity(StatusCode.OK,true,"增加成功");
	}
	
	/**
	 * 修改
	 * @param admin
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public ResultEntity update(@RequestBody Admin admin, @PathVariable String id ){
		admin.setId(id);
		adminService.update(admin);		
		return new ResultEntity(StatusCode.OK,true,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public ResultEntity delete(@PathVariable String id ){
		adminService.deleteById(id);
		return new ResultEntity(StatusCode.OK,true,"删除成功");
	}
	
}
