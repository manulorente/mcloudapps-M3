package es.codeurjc.books.restclients;


import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.codeurjc.books.models.User;

@FeignClient(name = "usersClient" ,url = "${users.client.endpoint}")
public interface UserClientMicroservice {

  @RequestMapping(method = RequestMethod.GET, value = "/users/{nick}")
  Optional<User> findByNick(@PathVariable("nick") String nick);

  @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}")
  Optional<User> findById(@PathVariable("userId") long userId);

}