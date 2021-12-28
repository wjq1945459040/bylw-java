package com.wjq.shiro;

import com.wjq.entity.Userinfo;
import com.wjq.service.UserinfoService;
import com.wjq.utils.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author wjq
 * @date 2021/12/12 17:08
 **/

@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserinfoService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {

        JwtToken jwtToken = (JwtToken) token;

        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();

        Userinfo user = userService.getById(userId);

        if (user == null) {
            throw new UnknownAccountException("账户不存在！");
        }

        if (("锁定").equals(user.getStatus())) {
            throw new LockedAccountException("账户被锁定！");
        }

        AccountProfile profile = new AccountProfile();

        BeanUtils.copyProperties(user, profile);

        return new SimpleAuthenticationInfo(profile, token.getCredentials(), getName());
    }
}
