package notes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import notes.model.token.Token;
import notes.model.token.TokenRepository;
import notes.model.user.User;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public String addToken(User user) {
        String token = UUID.randomUUID().toString();
        tokenRepository.save(new Token(token, user));
        return token;
    }

    public Optional<User> findUserByToken(String token) {
        return tokenRepository.findById(token).map(Token::getUser);
    }

}
