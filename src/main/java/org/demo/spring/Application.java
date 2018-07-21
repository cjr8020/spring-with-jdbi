package org.demo.spring;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }

    // ---------------------  DBI 2 ---------------------------

//    @Autowired
//    @Bean
//    public DBI dbi(DataSource dataSource) {
//        synchronized (DBI.class) {
//            return new DBI(dataSource);
//        }
//    }

//    @Autowired
//    @Bean
//    public DBIFactoryBean dbiFactory(DataSource dataSource) {
//        DBIFactoryBean dbiFactoryBean = new DBIFactoryBean();
//        dbiFactoryBean.setDataSource(dataSource);
//        return dbiFactoryBean;
//    }

    // ---------------------- DBI 3 ---------------------------

    /**
     * JDBI.
     *
     * @param dataSource DataSource
     * @return Jdbi instance
     */
    @Autowired
    @Bean
    public Jdbi dbi(DataSource dataSource) {
        synchronized (Jdbi.class) {
            Jdbi jdbi = Jdbi.create(dataSource);
            jdbi.installPlugin(new SqlObjectPlugin());
            return jdbi;
        }
    }


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}
