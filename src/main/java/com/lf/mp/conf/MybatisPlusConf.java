package com.lf.mp.conf;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/9 17:24
 * @Description :
 */
@Configuration
public class MybatisPlusConf {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 3.1.1 版本以前需要配置，之后的不需要配置
     *
     * @return
     */
    @Bean
    public ISqlInjector iSqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * 配置乐观锁
     *
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 性能分析插件：生产环境不开启
     * 会显示 sql的执行时间等信息
     * Time：66 ms - ID：com.lf.mp.dao.UsersMapper.selectList
     * Execute SQL：
     * SELECT
     * id,
     * name,
     * age,
     * email,
     * manager_id,
     * create_time,
     * update_time,
     * version
     * FROM
     * users
     * WHERE
     * deleted=0
     *
     * @return
     */
    @Bean
    @Profile({"deve", "test"})
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        //打印结果格式化
        performanceInterceptor.setFormat(true);
        //慢sql校验调试，若超过则会报错 5ms,有助于发现问题
//        performanceInterceptor.setMaxTime(5L);
        return performanceInterceptor;
    }
}
