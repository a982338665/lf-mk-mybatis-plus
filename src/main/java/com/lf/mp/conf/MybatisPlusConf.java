package com.lf.mp.conf;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/9 17:24
 * @Description :
 */
@Configuration
public class MybatisPlusConf {


    /**
     * 分页插件配置
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor interceptor = new PaginationInterceptor();
        ArrayList<ISqlParser> iSqlParsers = new ArrayList<>();
        //多租户sql解析器
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(new TenantHandler() {
            @Override
            public Expression getTenantId() {
                return new LongValue(1088248166370832385L);
            }

            @Override
            public String getTenantIdColumn() {
                return "manager_id";
            }

            /**
             * 是否加入租户信息
             * @param tableName
             * @return
             */
            @Override
            public boolean doTableFilter(String tableName) {
                //表示在操作表 user时 ，不加入租户信息查询
                if(tableName.equals("user")){
                    return true;
                }
                return false;
            }
        });
        iSqlParsers.add(tenantSqlParser);
        interceptor.setSqlParserList(iSqlParsers);
        return interceptor;
    }

    /**
     * 3.1.1 版本以前需要配置，之后的不需要配置
     * 逻辑删除配置
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
