package notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import notes.model.user.UserForm;
import notes.model.user.UserRepository;

@Controller
public class MainController {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MainController(NoteRepository noteRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String main(Model model, Principal principal) {
        User user = userRepository.findById(principal.getName()).get();
        model.addAttribute("notes", user.getNotes());
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
        User user = userRepository.findById(principal.getName()).get();
        noteRepository.save(new Note(form.getTitle(), form.getContent(), user));
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model, Principal principal) {
        User user = userRepository.findById(principal.getName()).get();
        return noteRepository.findById(id)
                .filter(x -> x.getUser().getUsername().equals(user.getUsername()))
                .map(x -> model.addAttribute("form", new NoteForm(x.getTitle(), x.getContent())))
                .map(x -> "update").orElse("redirect:/");
    }

    @PostMapping("/update/{id}")
    public String updateNote(@PathVariable Long id, @ModelAttribute("form") @Valid NoteForm form,
                             BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "update";
        }
        User user = userRepository.findById(principal.getName()).get();
        Note note = new Note(form.getTitle(), form.getContent(), user);
        note.setId(id);
        if (user.getNotes().stream().anyMatch(x -> x.getId().equals(id))) {
            noteRepository.save(note);
        }
        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Principal principal) {
        User user = userRepository.findById(principal.getName()).get();
        if (user.getNotes().stream().anyMatch(x -> x.getId().equals(id))) {
            noteRepository.deleteById(id);
        }
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
        userRepository.save(new User(form.getUsername(), passwordEncoder.encode(form.getPassword())));
        return "redirect:/";
    }

}
