package com.lf.mp.dao;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lf.mp.entity.User;
import com.lf.mp.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : Mr huangye
 * @createTime : 2020/7/9 11:30
 */
public interface UsersMapper extends BaseMapper<Users> {

    /**
     * SqlParser(filter = true) 表示不增加租户信息
     * 若版本在3.1.1之前，则需要做全局配置
     * @param wrapper
     * @return
     */
    @SqlParser(filter = true)
    @Select("select * from users ${ew.customSqlSegment}")
    List<Users> selectAll(@Param(Constants.WRAPPER) Wrapper<User> wrapper);

    @Select("select * from users ${ew.customSqlSegment}")
    IPage<Users> selectUsersPages(Page<User> userPage, @Param(Constants.WRAPPER) Wrapper<User> wrapper);

    /**
     * 删除所有
     * @return 返回影响行数
     */
    int deleteAll();
}
