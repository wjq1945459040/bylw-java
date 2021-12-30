package com.wjq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjq.cache.MybatisPlusRedisCache;
import com.wjq.entity.Userinfo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wjq
 * @since 2021-12-26
 */

@Repository
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface UserinfoMapper extends BaseMapper<Userinfo> {

}
