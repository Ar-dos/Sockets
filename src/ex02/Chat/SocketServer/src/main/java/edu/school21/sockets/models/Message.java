package edu.school21.sockets.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private Long identifier;
    private User author;
    private String text;
    private LocalDateTime creationTime;

    public Message() {
    }

    public Message(Long identifier, User author, String text, LocalDateTime creationTime) {
        this.identifier = identifier;
        this.author = author;
        this.text = text;
        this.creationTime = creationTime;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(identifier, message.identifier) && Objects.equals(author, message.author) && Objects.equals(text, message.text) && Objects.equals(creationTime, message.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, author, text, creationTime);
    }

    @Override
    public String toString() {
        return "Message{" +
                "identifier=" + identifier +
                ", author=" + author +
                ", text='" + text + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }
}
