package edu.school21.sockets.services;

import edu.school21.sockets.models.Room;
import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.MessagesRepository;
import edu.school21.sockets.repositories.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomsServiceImpl implements RoomsService {
    @Autowired
    private RoomsRepository roomsRepository;
    @Override
    public List<Room> listAllRooms() {
        return roomsRepository.findAll();
    }

    @Override
    public void enterRoom(User user, Long ig) {

    }
}
