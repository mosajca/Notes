package notes.model.note;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    Page<Note> findByUser_Username(String username, Pageable pageable);

    Optional<Note> findByIdAndUser_Username(Long id, String username);

}
