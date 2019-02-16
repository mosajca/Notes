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

import javax.validation.Valid;

import notes.model.Note;
import notes.model.NoteForm;
import notes.model.NoteRepository;
import notes.model.User;
import notes.model.UserForm;
import notes.model.UserRepository;

@Controller
public class MainController {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    @Autowired
    public MainController(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("notes", noteRepository.findAll());
        return "main";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("form", new NoteForm());
        return "add";
    }

    @PostMapping("/add")
    public String addNote(@ModelAttribute("form") @Valid NoteForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "add";
        }
        noteRepository.save(new Note(form.getTitle(), form.getContent()));
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        return noteRepository.findById(id)
                .map(x -> model.addAttribute("form", new NoteForm(x.getTitle(), x.getContent())))
                .map(x -> "update").orElse("redirect:/");
    }

    @PostMapping("/update/{id}")
    public String updateNote(@PathVariable Long id, @ModelAttribute("form") @Valid NoteForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "update";
        }
        Note note = new Note(form.getTitle(), form.getContent());
        note.setId(id);
        noteRepository.save(note);
        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        noteRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("form", new UserForm());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("form") @Valid UserForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        userRepository.save(new User(form.getName(), form.getPassword()));
        return "redirect:/";
    }

}
