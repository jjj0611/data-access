package org.kiworkshop.config;

import com.ibatis.sqlmap.client.SqlMapClient;
import org.kiworkshop.dao.MemberDao;
import org.kiworkshop.ibatis.MemberDaoIBatisImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.ibatis.SqlMapClientFactoryBean;

import javax.sql.DataSource;

@Configuration
@Import(DataSourceConfig.class)
public class IBatisConfig {
    @Bean
    public MemberDao memberDaoIBatisImpl(SqlMapClient sqlMapClient) {
        return new MemberDaoIBatisImpl(sqlMapClient);
    }

    @Bean
    public SqlMapClientFactoryBean sqlMapClient(DataSource dataSource) {
        SqlMapClientFactoryBean sqlMapClientFactoryBean = new SqlMapClientFactoryBean();
        sqlMapClientFactoryBean.setDataSource(dataSource);
        Resource resource = new ClassPathResource("/SqlMapConfig.xml");
        sqlMapClientFactoryBean.setConfigLocation(resource);
        return sqlMapClientFactoryBean;
    }
}
