package com.lf.mp.dao.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

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

    /**
     * 插入所有字段
     * 批量插入所有字段，排除逻辑删除字段不插入,
     * @param list
     * @return
     */
    int insertBatchSomeColumn(List<T> list);
}
