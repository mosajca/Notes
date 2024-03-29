package notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import javax.validation.Valid;

import notes.model.note.Note;
import notes.model.note.NoteForm;
import notes.service.NoteService;

@Controller
@RequestMapping("notes")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public String showNotes(Model model, Principal principal, @RequestParam(defaultValue = "0") int page) {
        Page<Note> pageOfNotes = noteService.findAll(principal.getName(), page);
        model.addAttribute("notes", pageOfNotes.getContent());
        model.addAttribute("totalPages", pageOfNotes.getTotalPages());
        return "show";
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
        noteService.save(principal.getName(), form);
        return "redirect:/notes";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model, Principal principal) {
        return noteService.findById(principal.getName(), id)
                .map(x -> model.addAttribute("form", new NoteForm(x.getTitle(), x.getContent())))
                .map(x -> "update").orElse("redirect:/");
    }

    @PostMapping("/update/{id}")
    public String updateNote(@PathVariable Long id, @ModelAttribute("form") @Valid NoteForm form,
                             BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "update";
        }
        noteService.update(principal.getName(), id, form);
        return "redirect:/notes";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Principal principal) {
        return noteService.findById(principal.getName(), id).map(x -> "delete").orElse("redirect:/");
    }

    @PostMapping("/delete/{id}")
    public String deleteNote(@PathVariable Long id, Principal principal) {
        noteService.delete(principal.getName(), id);
        return "redirect:/notes";
    }

}
