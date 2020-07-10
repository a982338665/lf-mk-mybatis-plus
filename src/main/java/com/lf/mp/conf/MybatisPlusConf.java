package com.lf.mp.conf;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/9 17:24
 * @Description :
 */
@Configuration
public class MybatisPlusConf {


    public static ThreadLocal<String> myTableName = new ThreadLocal<>();

    /**
     * 分页插件配置
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor interceptor = new PaginationInterceptor();
        ArrayList<ISqlParser> iSqlParsers = new ArrayList<>();
        //动态表名实现:3.2.0版本有此类，3.1.0没有此类 3.1.2有此类
        dynamicTableName(iSqlParsers);
        //多租户sql解析器
        // 表级别的 - 多租户sql过滤
//        setSQLParser(interceptor, iSqlParsers);
        // 方法级别的 多租户sql过滤
        setMethodParser(interceptor);
        interceptor.setSqlParserList(iSqlParsers);
        return interceptor;
    }

    private void dynamicTableName(ArrayList<ISqlParser> iSqlParsers) {
        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
        Map<String, ITableNameHandler> map = new HashMap<>();
        map.put("users", new ITableNameHandler() {
            //返回值为替换后的表名
            @Override
            public String dynamicTableName(MetaObject metaObject, String sql, String tableName) {
                //如果此处获取到的值为null，则不替换，仍为原来内容
                return myTableName.get();
            }
        });
        dynamicTableNameParser.setTableNameHandlerMap(map);
        iSqlParsers.add(dynamicTableNameParser);
    }

    private void setSQLParser(PaginationInterceptor interceptor, ArrayList<ISqlParser> iSqlParsers) {
        TenantSqlParser tenantSqlParser = getTenantSqlParser();
        iSqlParsers.add(tenantSqlParser);
    }

    private void setMethodParser(PaginationInterceptor interceptor) {
        interceptor.setSqlParserFilter(new ISqlParserFilter() {
            @Override
            public boolean doFilter(MetaObject metaObject) {
                MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
                //方法级别的过滤，此方法不加租户信息：查询作为条件，修改作为参数
                //同时表名 也不会被替换，原来什么样之后什么样
                if ("com.lf.mp.dao.UserMapper.selectById".equals(ms.getId())) {
                    return true;
                }
                return false;
            }
        });
    }

    private TenantSqlParser getTenantSqlParser() {
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        /**
         * 类级别的多租户使用
         */
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
                if (tableName.equals("user")) {
                    return true;
                }
                return false;
            }
        });
        return tenantSqlParser;
    }

    /**
     * 3.1.1 版本以前需要配置，之后的不需要配置
     * 逻辑删除配置
     * bean of type 'com.baomidou.mybatisplus.core.injector.ISqlInjector' available: expected single matching bean but found 2: mySqlInjector,iSqlInjector
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
     * 性能分析插件：生产环境不开启  3.2.0版本没有此类
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
