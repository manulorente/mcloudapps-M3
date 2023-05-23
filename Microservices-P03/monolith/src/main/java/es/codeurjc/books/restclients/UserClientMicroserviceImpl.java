package es.codeurjc.books.restclients;

import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.codeurjc.books.models.User;

public class UserClientMicroserviceImpl implements UserClient {

    private UserClientMicroservice UserClientMicroservice;

    public UserClientMicroserviceImpl(UserClientMicroservice UserClientMicroservice) {
        this.UserClientMicroservice = UserClientMicroservice;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{nick}")
    public Optional<User> findByNick(@PathVariable("nick") String nick) {
        return UserClientMicroservice.findByNick(nick);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}")
    public Optional<User> findById(@PathVariable("userId") long userId) {
        return UserClientMicroservice.findById(userId);

    }

}