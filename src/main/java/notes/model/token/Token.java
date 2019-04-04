package notes.model.token;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import notes.model.user.User;

@Entity
@Table(name = "tokens")
public class Token {

    @Id
    private String token;

    @OneToOne
    @JoinColumn(name = "username")
    private User user;

    public Token() {
    }

    public Token(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
