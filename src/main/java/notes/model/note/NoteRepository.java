package notes.model.note;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByUser_Username(String username);

    Optional<Note> findByIdAndUser_Username(Long id, String username);

}
