package Syeta;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class People {
    @NaturalId
    private String login;
    private String password;
    private String email;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public People(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }
    public People(int id, String login, String password, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public People(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public People(){

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
