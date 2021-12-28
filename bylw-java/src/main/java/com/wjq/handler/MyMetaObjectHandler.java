package com.wjq.handler;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.wjq.common.dto.LoginDto;
import com.wjq.entity.Userinfo;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * userinfo数据填充策略
 * @author wjq
 * @date 2021/12/25 21:59
 **/

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createDate", new Date(), metaObject);
        this.setFieldValByName("operateDate",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("operateDate",new Date(),metaObject);
    }
}
