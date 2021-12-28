package com.wjq.utils;

import com.wjq.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * 
 * @author wjq
 * @date 2021/12/13 21:09
 **/

public class ShiroUtils {
    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
