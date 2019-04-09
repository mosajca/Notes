package notes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import notes.model.user.User;
import notes.model.user.UserForm;
import notes.model.user.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final EmailService emailService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       TokenService tokenService, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.emailService = emailService;
    }

    public boolean existsByUsernameOrEmail(String username, String email) {
        return userRepository.existsByUsernameOrEmail(username, email);
    }

    public void saveAndSendActivationLink(UserForm form) {
        User user = userRepository.save(new User(form.getUsername(), passwordEncoder.encode(form.getPassword()), form.getEmail()));
        emailService.sendActivationLink(tokenService.addToken(user), user.getEmail());
    }

    public void activate(String token) {
        tokenService.findUserByToken(token).ifPresent(user -> {
            user.setEnabled(true);
            userRepository.save(user);
        });
    }

}
