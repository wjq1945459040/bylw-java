package com.wjq.service;

/**
 * <p>
 * 邮件服务接口
 * </p>
 *
 * @author wjq
 * @since 2021-12-26
 */

public interface MsmService {
    boolean sent(String email, String title, String code);
}
