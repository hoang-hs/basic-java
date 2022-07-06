package src.book.adapter.repositories.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import src.book.adapter.models.userModel;

import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<userModel, Long> {
    Optional<userModel> findByUsername(String username);

    Optional<userModel> findByUsernameAndPassword(String username, String password);
}
