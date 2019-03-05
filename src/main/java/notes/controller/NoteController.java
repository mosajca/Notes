package notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import javax.validation.Valid;

import notes.model.note.Note;
import notes.model.note.NoteForm;
import notes.model.note.NoteRepository;
import notes.model.user.User;

@Controller
public class NoteController {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping("/")
    public String main(Model model, Principal principal) {
        model.addAttribute("notes", noteRepository.findByUser_Username(principal.getName()));
        return "main";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("form", new NoteForm());
        return "add";
    }

    @PostMapping("/add")
    public String addNote(@ModelAttribute("form") @Valid NoteForm form, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "add";
        }
        noteRepository.save(new Note(form.getTitle(), form.getContent(), new User(principal.getName())));
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model, Principal principal) {
        return noteRepository.findByIdAndUser_Username(id, principal.getName())
                .map(x -> model.addAttribute("form", new NoteForm(x.getTitle(), x.getContent())))
                .map(x -> "update").orElse("redirect:/");
    }

    @PostMapping("/update/{id}")
    public String updateNote(@PathVariable Long id, @ModelAttribute("form") @Valid NoteForm form,
                             BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "update";
        }
        noteRepository.findByIdAndUser_Username(id, principal.getName()).map(x -> {
            x.setTitle(form.getTitle());
            x.setContent(form.getContent());
            return x;
        }).ifPresent(noteRepository::save);
        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Principal principal) {
        noteRepository.findByIdAndUser_Username(id, principal.getName()).ifPresent(noteRepository::delete);
        return "redirect:/";
    }

}
