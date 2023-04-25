package edu.school21.sockets.models;

public class User {
    private Long Identifier;
    private String Email;

    private String Password;

    public User() {
        Identifier = 0L;
        Email = "";
        Password = "";
    }

    public User(Long identifier, String email, String password) {
        Identifier = identifier;
        Email = email;
        Password = password;
    }

    public Long getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(Long identifier) {
        Identifier = identifier;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
