package com.bosen.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.bosen.common.handler.IntegerArrayJsonTypeHandler;
import com.bosen.common.handler.LongArrayJsonTypeHandler;
import com.bosen.common.handler.StringArrayJsonTypeHandler;
import com.bosen.common.util.TenantContext;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableTransactionManagement
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        // 新版分页配置
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 租户框架，3.4版本之后，mp的租户框架有调整，原来的ISqlParser被弃用，与此同时，@SqlParser也被弃用
//        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {
//            @Override
//            public Expression getTenantId() {
//                return new StringValue(TenantContext.getTenant());
//            }
//            @Override
//            public String getTenantIdColumn() {
//                return "tenant_id";
//            }
//            @Override
//            public boolean ignoreTable(String tableName) {
//                // 过滤哪些表
//                List<String> tableNameList = Collections.singletonList("bs_tenant");
//                if (tableNameList.contains(tableName)){
//                    return true;
//                }
//                // 如果需要针对sql进行过滤，需要手写mapper方法，在方法上添加注解@InterceptorIgnore(tenantLine = "true")
//                return false;
//            }
//
//        }));
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> {
            // 全局注册自定义TypeHandler
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            typeHandlerRegistry.register(String[].class, JdbcType.OTHER, StringArrayJsonTypeHandler.class);
            typeHandlerRegistry.register(Long[].class, JdbcType.OTHER, LongArrayJsonTypeHandler.class);
            typeHandlerRegistry.register(Integer[].class, JdbcType.OTHER, IntegerArrayJsonTypeHandler.class);
        };
    }
}
