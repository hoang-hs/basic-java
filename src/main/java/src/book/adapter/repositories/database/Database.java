package src.book.adapter.repositories.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import src.book.adapter.models.userModel;

@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner initDatabase(userRepository userRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) {
                userModel userModel1 = new userModel(1L, "user1", "123456");
                userModel userModel2 = new userModel(2L, "user2", "123456");
                logger.info("insert data: " + userRepository.save(userModel1));
                logger.info("insert data: " + userRepository.save(userModel2));
            }
        };
    }
}
