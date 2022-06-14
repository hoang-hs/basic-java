package src.book.adapter.repositories.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import src.book.adapter.models.userModel;

import java.util.List;

@Repository
public interface userRepository extends JpaRepository<userModel, Long> {

}
