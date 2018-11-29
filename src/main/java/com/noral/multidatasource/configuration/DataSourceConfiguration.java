package com.noral.multidatasource.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @Author hu
 * @Description:
 * @Date Create In 18:29 2018/11/28 0028
 */
@Configuration
public class DataSourceConfiguration {
    @Value("${mysql.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name = "masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "mysql.datasource.master")
    public DataSource masterDateSource() {
        DataSource dataSource = DataSourceBuilder.create().type(dataSourceType).build();
        return dataSource;

    }

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "mysql.datasource.slave")
    public DataSource slaveDateSource() {
        DataSource dataSource = DataSourceBuilder.create().type(dataSourceType).build();
        return dataSource;
    }

}