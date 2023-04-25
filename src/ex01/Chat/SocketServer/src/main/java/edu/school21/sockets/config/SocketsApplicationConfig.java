package edu.school21.sockets.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.sockets.repositories.*;
import edu.school21.sockets.server.Server;
import edu.school21.sockets.services.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db.properties")
public class SocketsApplicationConfig {
    @Value("${db.url}") String url;
    @Value("${db.user}") String user;
    @Value("${db.password}") String password;

    @Value("${db.driver.name}") String name;

    private DataSource dataSource() {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);
        config.setMaximumPoolSize(10);
        config.setAutoCommit(true);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return new HikariDataSource(config);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UsersRepository usersRepository() {
        return new UsersRepositoryImpl(dataSource());
    }

    @Bean
    public UsersService usersService() {
        return new UsersServiceImpl();
    }
    @Bean
    public MessagesRepository messagesRepository() {
        return new MessagesRepositoryImpl(dataSource());
    }
    @Bean
    public MessagesService messagesService() {
        return new MessagesServiceImpl();
    }
    @Bean
    public Server server() {
        return new Server();
    }

}