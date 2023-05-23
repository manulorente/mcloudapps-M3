package es.codeurjc.books.restclients;

import es.codeurjc.books.models.User;
import java.util.Optional;

public interface UserClient {
  Optional<User> findByNick(String nick);

  Optional<User> findById(long userId);
}