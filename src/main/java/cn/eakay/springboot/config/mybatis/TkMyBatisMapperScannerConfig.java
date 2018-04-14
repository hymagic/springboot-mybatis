/*
 * auth jiangleixian.
 */

package cn.eakay.springboot.config.mybatis;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * MyBatis扫描接口，使用的tk.mybatis.spring.mapper.MapperScannerConfigurer，如果你不使用通用Mapper
 */
@Configuration
@AutoConfigureAfter(value = {DataSourceSqlSessionFactoryConfig.class})
public class TkMyBatisMapperScannerConfig
{


    @Bean
    public MapperScannerConfigurer masterMapperScannerConfigurer()
    {

        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("masterSqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("cn.eakay.springboot.repository.mybaits.master");
        //mapperScannerConfigurer.setBasePackage(masterPackage);
        Properties properties = new Properties();
        properties.setProperty("mappers", "cn.eakay.springboot.repository.BaseMapper");
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }

    @Bean
    public MapperScannerConfigurer slaveMapperScannerConfigurer()
    {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("slaveSqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("cn.eakay.springboot.repository.mybaits.slave");
        //mapperScannerConfigurer.setBasePackage(slavePackage);
        Properties properties = new Properties();
        properties.setProperty("mappers", "cn.eakay.springboot.repository.BaseMapper");
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }
}
