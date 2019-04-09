package notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import notes.model.user.UserForm;
import notes.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("form", new UserForm());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("form") @Valid UserForm form, BindingResult result) {
        if (result.hasErrors() || userService.existsByUsernameOrEmail(form.getUsername(), form.getEmail())) {
            return "register";
        }
        userService.saveAndSendActivationLink(form);
        return "redirect:/";
    }

    @GetMapping("/activate")
    public String activateUser(@RequestParam String token) {
        userService.activate(token);
        return "redirect:/";
    }

}
