package com.wjq.controller;

import com.wjq.common.lang.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wjq
 * @date 2021/12/27 22:09
 **/

@RestController
public class SelectItemsRest {

    //模块
    @PostMapping("/getAuthority")
    public Result getAuthority() {

        Map<String, Object> map = new HashMap<>();
        map.put("xx模块", "0");
        map.put("论坛模块", "1");
        map.put("电影模块", "2");

        return Result.success(map);
    }

    //用户状态，从后台获取
    @PostMapping("/getUserStatus")
    public Result getUserStatus() {

        Map<String, Object> map;
        List<Map<String, Object>> res = new ArrayList<>();
        String[] keys = {"锁定", "登陆中", "已注销"};

        for (int i = 0; i < keys.length; i++) {
            map = new HashMap<>();
            map.put("text", keys[i]);
            map.put("value", i + 1);
            res.add(map);
        }

        return Result.success(res);
    }
}
