package com.poscloud.transactionprocessingtool.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 *
 * <p> Config for TapCard database connection </p>
 *
 * @author Samuel Gwokuda <samuel@poscloud.co.zw>
 * @author Takaedza Hakunavanhu <takaedza@poscloud.co.zw>
 * @version v1.0
 * @since v1.0
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.poscloud.transactionprocessingtool.repository.tapcard"}
)
public class TapCardDbConfig {


    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "tapcard.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(final EntityManagerFactoryBuilder builder, @Qualifier("dataSource") final DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.poscloud.transactionprocessingtool.domain.tapcard")
                .persistenceUnit("tapcard")
                .build();
    }


    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") final EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}