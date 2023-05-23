package es.codeurjc.books.restclients;

import java.util.Optional;

import es.codeurjc.books.models.User;
import es.codeurjc.books.repositories.UserRepository;


public class UserClientRepository implements UserClient {
    private UserRepository userRepository;

    public UserClientRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByNick(String nick) {
        return userRepository.findByNick(nick);
    }

    public Optional<User> findById(long userId) {
        return userRepository.findById(userId);
    }

}