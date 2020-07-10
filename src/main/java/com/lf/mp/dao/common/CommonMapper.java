package com.lf.mp.dao.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.injector.methods.additional.InsertBatchSomeColumn;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 根据 id 逻辑删除数据,并带字段填充功能
     * <p>注意入参是 entity !!! ,如果字段没有自动填充,就只是单纯的逻辑删除</p>
     * <p>
     * 自己的通用 mapper 如下使用:
     * <pre>
     * int deleteByIdWithFill(T entity);
     * </pre>
     * </p>
     *
     * @author miemie
     * @since 2018-11-09
     */
    int deleteByIdWithFill(T entity);

    /**
     * 根据 ID 更新固定的那几个字段(但是不包含逻辑删除)
     *
     * <p>
     * 自己的通用 mapper 如下使用:
     * <pre>
     * int alwaysUpdateSomeColumnById(@Param(Constants.ENTITY) T entity);
     * </pre>
     * </p>
     *
     * <p> 如何筛选字段参考请 {@link InsertBatchSomeColumn} 里面的注释 </p>
     *
     * @author hubin
     * @since 2019-04-12
     */
    int alwaysUpdateSomeColumnById(@Param(Constants.ENTITY) T entity);
}
