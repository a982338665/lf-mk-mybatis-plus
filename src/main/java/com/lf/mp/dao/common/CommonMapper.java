package com.lf.mp.dao.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author : Mr huangye
 * @createTime : 2020/7/10 14:39
 */
public interface CommonMapper<T> extends BaseMapper<T> {

    /**
     * 删除所有
     * @return 返回影响行数
     */
    int deleteAll();
}
