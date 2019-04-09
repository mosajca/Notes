package notes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import notes.model.note.Note;
import notes.model.note.NoteForm;
import notes.model.note.NoteRepository;
import notes.model.user.User;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> findAll(String username) {
        return noteRepository.findByUser_Username(username);
    }

    public Optional<Note> findById(String username, Long id) {
        return noteRepository.findByIdAndUser_Username(id, username);
    }

    public void save(String username, NoteForm form) {
        noteRepository.save(new Note(form.getTitle(), form.getContent(), new User(username)));
    }

    public void update(String username, Long id, NoteForm form) {
        noteRepository.findByIdAndUser_Username(id, username).map(note -> {
            note.setTitle(form.getTitle());
            note.setContent(form.getContent());
            return note;
        }).ifPresent(noteRepository::save);
    }

    public void delete(String username, Long id) {
        noteRepository.findByIdAndUser_Username(id, username).ifPresent(noteRepository::delete);
    }

}
