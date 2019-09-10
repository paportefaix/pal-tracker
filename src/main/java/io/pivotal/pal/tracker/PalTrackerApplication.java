package io.pivotal.pal.tracker;

import com.mysql.cj.jdbc.MysqlDataSource;
import io.pivotal.pal.tracker.controllers.TimeEntryController;
import io.pivotal.pal.tracker.repository.InMemoryTimeEntryRepository;
import io.pivotal.pal.tracker.repository.JdbcTimeEntryRepository;
import io.pivotal.pal.tracker.repository.TimeEntryRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

@SpringBootApplication
public class PalTrackerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PalTrackerApplication.class, args);
        DataSource bean = context.getBean(DataSource.class);
        System.out.println(bean);
    }

    @Bean
    TimeEntryRepository timeEntryRepository(DataSource dataSource) {
        return new JdbcTimeEntryRepository(dataSource);
    }
}
