package edu.school21.sockets.models;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.List;

public class Room {
    private Long identifier;
    private String name;
    private User owner;
    private List<Message> messages;

    public Long getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setIdentifier(Long id) {
        this.identifier = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Room(Long id, String name, User owner, List<Message> messages) {
        this.identifier = id;
        this.name = name;
        this.owner = owner;
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room chatroom = (Room) o;
        return identifier.equals(chatroom.identifier) && name.equals(chatroom.name) && owner.equals(chatroom.owner) && Objects.equals(messages, chatroom.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, name, owner, messages);
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "id=" + identifier +
                ", name='" + name + '\'' +
                ", owner=" + owner.getUsername() +
                ", messages=" + messages +
                '}';
    }
}


