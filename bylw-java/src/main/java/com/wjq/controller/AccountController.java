package com.wjq.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wjq.common.dto.LoginDto;
import com.wjq.common.lang.Result;
import com.wjq.entity.Userinfo;
import com.wjq.service.MsmService;
import com.wjq.service.UserinfoService;
import com.wjq.utils.JwtUtils;
import com.wjq.utils.RandomUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 登录接口编写
 *
 * @author wjq
 * @date 2021/12/13 15:11
 **/

@RestController
public class AccountController {

    @Autowired
    UserinfoService userService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    MsmService msmService;

    //登录
    @CrossOrigin
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {

        QueryWrapper<Userinfo> wrapper = new QueryWrapper<>();
        wrapper.eq("logid", loginDto.getLogid());
        Userinfo user = userService.getOne(wrapper);
        Assert.notNull(user, "用户不存在");
        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("密码错误");
        }

        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        return Result.success(MapUtil.builder()
                                     .put("nickname", user.getNickname())
                                     .put("authority", user.getAuthority())
                                     .put("id", user.getId())
                                     .put("logid", user.getLogid())
                                     .put("email", user.getEmail())
                                     .put("avatar", user.getAvatar()).map());

    }

    //注册
    @CrossOrigin
    @PostMapping("/register")
    public Result register(@Validated @RequestBody LoginDto loginDto) {

        QueryWrapper<Userinfo> wrapper = new QueryWrapper<>();
        wrapper.eq("logid", loginDto.getLogid());
        Userinfo user = userService.getOne(wrapper);

        if (user != null) {
            return Result.fail("用户已存在,请重新输入!");
        }

        Userinfo userinfo = new Userinfo();

        BeanUtils.copyProperties(loginDto, userinfo);

        userinfo.setPassword(SecureUtil.md5(loginDto.getPassword()));

        userService.save(userinfo);

        return Result.success("创建成功");

    }

    /*
     * 注册发送验证码
     */
    @GetMapping("/sentEmail/{email}")
    public Result sendMsg(@PathVariable String email) {

        String code = redisTemplate.opsForValue().get(email);
        if (!StringUtils.isEmpty(code)) {
            return Result.success();
        }

        code = RandomUtil.getFourBitRandom();
        boolean flag = msmService.sent(email, "注册验证码", code);
        if (flag) {
            redisTemplate.opsForValue().set(email, code, 1, TimeUnit.MINUTES);
            return Result.success("短信发送成功");
        }
        else {
            return Result.fail("短信发送失败");
        }
    }

    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();

        return Result.success(null);
    }
}
