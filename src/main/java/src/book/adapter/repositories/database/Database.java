package src.book.adapter.repositories.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import src.book.adapter.models.userModel;
import src.book.api.requests.userRequest;
import src.book.core.entities.userEntity;
import src.book.core.usecases.insertUserUseCase;

@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

//    private final insertUserUseCase insertUserUsecase;

//    @Autowired
//    public Database(insertUserUseCase insertUserUsecase) {
//        this.insertUserUsecase = insertUserUsecase;
//    }

    @Bean
    CommandLineRunner initDatabase(insertUserUseCase insertUserUseCase) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) {
//                userModel userModel1 = new userModel(1L, "user1", "123456");
//                userModel userModel2 = new userModel(2L, "user2", "123456");
//                logger.info("insert data: " + userRepository.save(userModel1));
//                logger.info("insert data: " + userRepository.save(userModel2));
                userRequest user1 = new userRequest("user1", "123");
                userRequest user2 = new userRequest("user2", "123");
                logger.info("insert data: " + insertUserUseCase.insertUser(user1));
                logger.info("insert data: " + insertUserUseCase.insertUser(user2));
            }
        };
    }
}
