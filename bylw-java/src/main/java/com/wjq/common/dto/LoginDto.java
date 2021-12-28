package com.wjq.common.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @author wjq
 * @date 2021/12/23 21:18
 **/

@Data
public class LoginDto implements Serializable {

    @NotNull(message = "用户名不能为空")
    private String logid;

    private String nickname;

    @NotNull(message = "密码不能为空")
    private String password;

}
