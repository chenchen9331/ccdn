package com.chenchen.base.controller;

import com.chenchen.base.pojo.Label;
import com.chenchen.base.service.LabelService;
import com.chenchen.common.entity.PageResultEntity;
import com.chenchen.common.entity.ResultEntity;
import com.chenchen.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标签Controller
 * @author chenchen
 */
@CrossOrigin
@RequestMapping("/label")
@RestController
public class LabelController {

    @Autowired
    private LabelService labelService;

    /**
     * 查询全部标签
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResultEntity findAll() {
        return new ResultEntity(StatusCode.OK, true, "查询成功", labelService.findAll());
    }

    /**
     * 根据Id查询标签
     * @param labelId
     * @return
     */
    @RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
    public ResultEntity findLabelById(@PathVariable("labelId") String labelId) {
        return new ResultEntity(StatusCode.OK, true, "查询成功", labelService.findLabelById(labelId));
    }

    /**
     * 保存标签
     * @param label
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultEntity save(@RequestBody Label label) {
        labelService.save(label);
        return new ResultEntity(StatusCode.OK, true, "保存成功");
    }

    /**
     * 根据Id更新标签
     * @param labelId
     * @param label
     * @return
     */
    @RequestMapping(value = "/{labelId}", method = RequestMethod.PUT)
    public ResultEntity update(@PathVariable("labelId") String labelId, @RequestBody Label label) {
        label.setId("labelId");
        labelService.update(label);
        return new ResultEntity(StatusCode.OK, true, "更新成功");
    }

    /**
     * 根据Id删除标签
     * @return
     */
    @RequestMapping(value = "/{labelId}", method = RequestMethod.DELETE)
    public ResultEntity deleteById(@PathVariable("labelId") String labelId) {
        labelService.delete(labelId);
        return new ResultEntity(StatusCode.OK, true, "删除成功");
    }

    /**
     * 根据查询条件查询
     * @param label
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResultEntity findBySearch(@RequestBody Label label) {
        List<Label> list = labelService.findBySearch(label);
        return new ResultEntity(StatusCode.OK, true, "查询成功", list);
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public PageResultEntity<Label> pageQuery(Label label, @PathVariable("page") int page, @PathVariable("size") int size) {
        Page<Label> pageDate = labelService.pageQuery(label, page, size);
        return new PageResultEntity<Label>(pageDate.getTotalElements(), pageDate.getContent());
    }
}
