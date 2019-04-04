package notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.UUID;

import notes.model.token.Token;
import notes.model.token.TokenRepository;
import notes.model.user.User;
import notes.model.user.UserForm;
import notes.model.user.UserRepository;
import notes.service.EmailService;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final TokenRepository tokenRepository;

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.tokenRepository = tokenRepository;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("form", new UserForm());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("form") @Valid UserForm form, BindingResult result) {
        if (result.hasErrors() || userRepository.existsByUsernameOrEmail(form.getUsername(), form.getEmail())) {
            return "register";
        }
        User user = userRepository.save(new User(form.getUsername(), passwordEncoder.encode(form.getPassword()), form.getEmail()));
        String token = UUID.randomUUID().toString();
        tokenRepository.save(new Token(token, user));
        emailService.sendActivationLink(token, user.getEmail());
        return "redirect:/";
    }

    @GetMapping("/activate")
    public String activateUser(@RequestParam String token) {
        tokenRepository.findById(token).map(Token::getUser).ifPresent(user -> {
            user.setEnabled(true);
            userRepository.save(user);
        });
        return "redirect:/";
    }

}
