package edu.school21.sockets.repositories;

import edu.school21.sockets.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class MessagesRepositoryImpl implements MessagesRepository {

    @Autowired
    private UsersRepository usersRepository;

    public final RowMapper<Message> MESSAGE_ROW_MAPPER = (rs, num) -> new Message(
            rs.getLong("id"),
            usersRepository.findById(rs.getLong("author_id")).get(),
            rs.getString("text"),
            rs.getObject("creation_time", LocalDateTime.class)
    );

    private final JdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    public MessagesRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Message> findById(Long id) {
        if (id == null) {
            return null;
        }

        return Optional.ofNullable(jdbcTemplate.query("SELECT * FROM messages WHERE id=?", MESSAGE_ROW_MAPPER,
                id).stream().findFirst().orElse(null));
    }

    @Override
    public List<Message> findAll() {
        return jdbcTemplate.query("SELECT * FROM messages ORDER BY id", MESSAGE_ROW_MAPPER);
    }


    public List<Message> findAllByRoomId(Long id) {
        return jdbcTemplate.query("SELECT * FROM messages WHERE chatroom_id=? ORDER BY id", MESSAGE_ROW_MAPPER,id);
    }

    @Override
    public void save(Message message) {
        if (message != null) {
            Long id = jdbcTemplate.query("INSERT INTO messages (author_id, text, date_time)" +
                            " VALUES (?, ?, ?) RETURNING id",
                    (rs, num) -> rs.getLong(1),
                    message.getAuthor().getIdentifier(),
                    message.getText(),
                    message.getCreationTime()
            ).stream().findFirst().orElse(null);
            message.setIdentifier(id);

        }
    }

    @Override
    public void update(Message message) {
        if (message != null) {
            jdbcTemplate.update("UPDATE messages SET author_id=?, text=?, date_time=? WHERE id=?",
                    message.getAuthor().getIdentifier(),
                    message.getText(),
                    message.getCreationTime(),
                    message.getIdentifier());
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            jdbcTemplate.update("DELETE FROM messages WHERE id=?", id);
        }
    }
}
