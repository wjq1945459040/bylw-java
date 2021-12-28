package com.wjq.shiro;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 
 * @author wjq
 * @date 2021/12/12 18:26
 **/

@Data
public class AccountProfile implements Serializable {
    private String id;

    private String logid;

    private String nickname;

    private String status;

    private String sex;

    private Integer age;

    private Date birthday;

    private String phone;

    private String signature;

    private String avatar;

    private String email;

    private Date createDate;

    private Date operateDate;
}
