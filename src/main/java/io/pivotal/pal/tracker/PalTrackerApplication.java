package io.pivotal.pal.tracker;

import com.mysql.cj.jdbc.MysqlDataSource;
import io.pivotal.pal.tracker.controllers.TimeEntryController;
import io.pivotal.pal.tracker.repository.InMemoryTimeEntryRepository;
import io.pivotal.pal.tracker.repository.JdbcTimeEntryRepository;
import io.pivotal.pal.tracker.repository.TimeEntryRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class PalTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PalTrackerApplication.class, args);
    }

    @Bean
    TimeEntryRepository inMemoRepo(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(System.getenv("SPRING_DATASOURCE_URL"));
        return new JdbcTimeEntryRepository(dataSource);
    }
}
