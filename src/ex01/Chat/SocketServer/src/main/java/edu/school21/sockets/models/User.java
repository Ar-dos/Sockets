package edu.school21.sockets.models;

public class User {
    private Long identifier;
    private String username;

    private String password;

    public User() {
        identifier = 0L;
        username = "";
        password = "";
    }

    public User(Long identifier, String username, String password) {
        this.identifier = identifier;
        this.username = username;
        this.password = password;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        identifier = identifier;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        password = password;
    }
}
