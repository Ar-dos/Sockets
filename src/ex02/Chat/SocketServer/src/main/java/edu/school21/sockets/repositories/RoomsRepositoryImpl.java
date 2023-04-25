package edu.school21.sockets.repositories;

import edu.school21.sockets.models.Room;
import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class RoomsRepositoryImpl implements RoomsRepository {

    @Autowired
    private static UsersRepository usersRepository;

    @Autowired
    private static MessagesRepository messagesRepository;
    public static final RowMapper<Room> ROOM_ROW_MAPPER = (rs, num) -> new Room(
            rs.getLong("id"),
            rs.getString("name"), null ,null
//            usersRepository.findById(rs.getLong("owner_id")).get(),
//            null
    );
    private final JdbcTemplate jdbcTemplate;

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private PasswordEncoder passwordEncoder;

    public RoomsRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Optional<Room> findById(Long id) {
        if (id == null) {
            return null;
        }

        return Optional.ofNullable(jdbcTemplate.query("SELECT * FROM chatrooms WHERE id=?", ROOM_ROW_MAPPER,
                id).stream().findFirst().orElse(null));
    }

    @Override
    public List<Room> findAll() {
        return jdbcTemplate.query("SELECT * FROM chatrooms ORDER BY id", ROOM_ROW_MAPPER);
    }


    @Override
    public void save(Room room) {
        if (room != null) {
            Long id = jdbcTemplate.query("INSERT INTO chatrooms (name, owner_id)" +
                            " VALUES (?, ?) RETURNING id",
                    (rs, num) -> rs.getLong(1),
                    room.getName(),
                    room.getOwner().getIdentifier()
            ).stream().findFirst().orElse(null);
            room.setIdentifier(id);
        }
    }

    @Override
    public void update(Room room) {
        if (room != null) {
            jdbcTemplate.update("UPDATE chatrooms SET name=?, owner_id=? WHERE id=?",
                    room.getName(),
                    room.getOwner().getIdentifier(),
                    room.getIdentifier());
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            jdbcTemplate.update("DELETE FROM chatrooms WHERE id=?", id);
        }
    }
}
