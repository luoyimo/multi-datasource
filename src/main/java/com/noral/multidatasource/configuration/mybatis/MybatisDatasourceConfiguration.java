package com.noral.multidatasource.configuration.mybatis;

import com.noral.multidatasource.configuration.DataSourceContextHolder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages = "com.noral.multidatasource.mapper", sqlSessionFactoryRef = "sqlSessionFactory1")
public class MybatisDatasourceConfiguration {

    @Autowired
    @Qualifier("slaveDataSource")
    private DataSource slaveDateSource;

    @Autowired
    @Qualifier("noralDataSource")
    private DataSource noralDataSource;

    @Autowired
    @Qualifier("masterDataSource")
    private DataSource masterDataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory1() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(routingDataSource());
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        return factoryBean.getObject();

    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory1());
        return template;
    }


    @Bean
    public AbstractRoutingDataSource routingDataSource() {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>(16);
        targetDataSources.put(DataSourceContextHolder.MASTER, masterDataSource);
        targetDataSources.put(DataSourceContextHolder.SLAVE, slaveDateSource);
        targetDataSources.put(DataSourceContextHolder.NORMAL, noralDataSource);
        // 路由类，寻找对应的数据源
        AbstractRoutingDataSource proxy = new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
                String key = DataSourceContextHolder.getMaterOrSlave();
                if (key == null) {
                    return DataSourceContextHolder.MASTER;
                }
                return key;
            }
        };

        // 默认库
        proxy.setDefaultTargetDataSource(masterDataSource);
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }

}
