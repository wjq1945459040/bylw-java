package com.wjq.controller;


import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjq.common.lang.Result;
import com.wjq.entity.Userinfo;
import com.wjq.service.UserinfoService;
import com.wjq.shiro.AccountProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wjq
 * @since 2021-12-26
 */
@RestController
public class UserinfoController {

    @Autowired
    UserinfoService userinfoService;

    @PostMapping("/userlist")
    public Result list(@RequestBody JSONObject json) {

        String name = json.getStr("name");
        String status = json.getStr("status");
        int pageIndex = Integer.parseInt(json.getStr("pageIndex"));
        int pageSize = Integer.parseInt(json.getStr("pageSize"));

        Page page = new Page(pageIndex, pageSize);

        QueryWrapper<Userinfo> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(name)) {
            wrapper.like("logid", name);
        }

        if (StringUtils.isNotEmpty(status) && !("0".equals(status))) {
            wrapper.eq("status", status);
        }
        wrapper.orderByDesc("create_date");
        IPage<Userinfo> pageData = userinfoService.page(page, wrapper);

        return new Result().success(pageData);
    }

    @PostMapping("/user/save")
    public Result save(@RequestBody AccountProfile edit) {

        Userinfo userinfo = userinfoService.getById(edit.getId());

        if (userinfo == null) {
            return new Result().fail("用户不存在");
        }

        BeanUtils.copyProperties(edit, userinfo, "logid", "id", "createDate");

        userinfoService.saveOrUpdate(userinfo);

        return new Result().success(null);
    }

    @PostMapping("/user/delete")
    public Result delete(@RequestParam String id) {

        String[] ids = id.split(",");

        if (ids.length == 1) {
            userinfoService.removeById(id);
            return new Result().success(null);
        }

        userinfoService.removeByIds(Arrays.asList(ids));
        return new Result().success(null);
    }
}
