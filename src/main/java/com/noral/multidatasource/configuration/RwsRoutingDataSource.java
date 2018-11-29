package com.noral.multidatasource.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author hu
 * @Description:
 * @Date Create In 10:38 2018/11/29 0029
 */
@Configuration
public class RwsRoutingDataSource {

    @Autowired
    @Qualifier("masterDataSource")
    private DataSource masterDataSource;

    @Autowired
    @Qualifier("slaveDataSource")
    private DataSource slaveDateSource;


    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(routingDataSource());
        return jdbcTemplate;
    }


    @Bean
    public AbstractRoutingDataSource routingDataSource() {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>(2);
        targetDataSources.put(DataSourceContextHolder.MASTER, masterDataSource);
        targetDataSources.put(DataSourceContextHolder.SLAVE, slaveDateSource);
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


    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(routingDataSource());
    }
}
