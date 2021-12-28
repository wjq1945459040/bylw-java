package com.wjq.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author wjq
 * @date 2021/12/12 17:30
 **/

public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
