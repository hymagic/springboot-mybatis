package cn.eakay.springboot.config.mybatis;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * tkmybatis配置参见{@link  TkMyBatisMapperScannerConfig}
 */
@Configuration
// 扫描 Mapper 接口并容器管理
//@MapperScan(basePackages = MasterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class DataSourceSqlSessionFactoryConfig
{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.datasource.mybatis.master.mapper-location}")
    private String masterMapperLocation;
    @Value("${spring.datasource.mybatis.slave.mapper-location}")
    private String slaveMapperLocation;
/*
    @Value("${master.datasource.url}")
    private String url;

    @Value("${master.datasource.userName}")
    private String userName;

    @Value("${master.datasource.passWord}")
    private String passWord;

    @Value("${master.datasource.driverClassName}")
    private String driverClass;

    @Value("${datasource.initialSize}")
    private int initialSize;

    @Value("${datasource.minIdle}")
    private int minIdle;

    @Value("${datasource.maxActive}")
    private int maxActive;

    @Value("${datasource.maxWait}")
    private int maxWait;

    @Value("${datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${datasource.validationQuery}")
    private String validationQuery;

    @Value("${datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${datasource.filters}")
    private String filters;

    @Value("${datasource.logSlowSql}")
    private String logSlowSql;*/

    /**
     * spring-boot-autoconfigure DataSourceAutoConfiguration  会初始化DataSourceInitializer 有一个init方法 会去获取DataSource(数据源)
     * 多数据源初始化方法中 会获取数据源 需要初始化一些ddl操作 runSchemaScripts()方法 检查初始化时是否需要执行sql script ,当你有两个数据源的时候，程序不知道取哪一个 ，所以报错
     * 解决方案
     * 1.定义数据源的地方 加个primary="true" 记得只给其中的一个加， 当多数据源时 标示这个数据源是主要的
     * 2.spring boot 启动类加上 exclude = DataSourceAutoConfiguration.class  代表启动项目的时候 不加载这个类
     *
     * @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
     */
 /*   @Bean(name = "masterDataSource")
    @Primary
    public DataSource masterDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(passWord);
        //初始化连接大小
        dataSource.setInitialSize(initialSize);
        //连接次最小使用连接数量
        dataSource.setMinIdle(minIdle);
        //连接池最大使用连接数量
        dataSource.setMaxActive(maxActive);
        //获取连接等待超时的时间
        dataSource.setMaxWait(maxWait);
        //用来检测连接是否有效的sql，要求是一个查询语句。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会其作用。
        dataSource.setValidationQuery(validationQuery);
        //建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
        dataSource.setTestWhileIdle(testWhileIdle);
        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        //配置一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        //申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
        dataSource.setTestOnBorrow(testOnBorrow);
        //归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
        dataSource.setTestOnReturn(testOnReturn);

        try {
            dataSource.setFilters(filters);
        } catch (SQLException e) {
            log.error("druid configuration initialization filter", e);
        }
        return dataSource;
    }*/


    //
    @Primary
    @Bean(name = "masterDataSource")
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource()
    {
        return DruidDataSourceBuilder.create().build();
    }


    @Primary
    @Bean(name = "masterTransactionManager")
    public DataSourceTransactionManager masterTransactionManager()
    {
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource)
            throws Exception
    {

        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(masterMapperLocation));
        return sessionFactory.getObject();
    }


    @Bean(name = "slaveDataSource")
    @ConfigurationProperties("spring.datasource.druid.slave")
    public DataSource slaveDataSource()
    {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "slaveTransactionManager")
    public DataSourceTransactionManager slaveTransactionManager()
    {
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory slaveSqlSessionFactory(@Qualifier("slaveDataSource") DataSource masterDataSource)
            throws Exception
    {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(slaveMapperLocation));

    /*
      集成pagehelper-spring-boot-starter  PageHelperAutoConfiguration已实现自动装配
      Interceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("params", "pageNum=pageNumKey;pageSize=pageSizeKey;");
        interceptor.setProperties(properties);
        sessionFactory.setPlugins(new Interceptor[]{interceptor});*/
        return sessionFactory.getObject();
    }


}