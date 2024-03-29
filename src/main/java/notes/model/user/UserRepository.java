package notes.model.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByUsernameOrEmailAllIgnoreCase(String username, String email);

}
