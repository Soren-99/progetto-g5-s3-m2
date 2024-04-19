package sorenrahimi.progetto.g5s3m2.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import sorenrahimi.progetto.g5s3m2.entities.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
