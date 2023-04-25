package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class UsersRepositoryImpl implements UsersRepository {
    public static final RowMapper<User> USER_ROW_MAPPER = (rs, num) -> new User(
            rs.getLong("id"),
            rs.getString("login"),
            rs.getString("password")
    );
    private final JdbcTemplate jdbcTemplate;

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findById(Long id) {
        if (id == null) {
            return null;
        } else {
            return Optional.ofNullable(jdbcTemplate.query("SELECT * FROM users WHERE id=?", USER_ROW_MAPPER,
                    id).stream().findFirst().orElse(null));
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users ORDER BY id", USER_ROW_MAPPER);
    }

    @Override
    public void save(User user) {
        if (user != null) {
            Long id = jdbcTemplate.query("INSERT INTO users (login, password) VALUES (?, ?) RETURNING id",
                    (rs, num) -> rs.getLong(1),
                    user.getUsername(), passwordEncoder.encode(user.getPassword())).stream().findFirst().orElse(null);
            user.setIdentifier(id);
        }
    }

    @Override
    public void update(User user) {
        if (user != null) {
            jdbcTemplate.update("UPDATE users SET login=?, password=? WHERE id=?",
                    user.getUsername(), user.getPassword(), user.getIdentifier());
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            jdbcTemplate.update("DELETE FROM users WHERE id=?", id);
        }
    }

    @Override
    public User findByUserName(String userName) {
        if (userName == null) {
            return null;
        } else {
            return jdbcTemplate.query("SELECT * FROM users WHERE login=?", USER_ROW_MAPPER,
                    userName).stream().findFirst().orElse(null);
        }
    }
}
