package edu.school21.sockets.services;

import edu.school21.sockets.models.Room;
import edu.school21.sockets.models.User;

import java.util.List;

public interface RoomsService {

    List<Room> listAllRooms();

    void enterRoom(User user, Long ig);
}
