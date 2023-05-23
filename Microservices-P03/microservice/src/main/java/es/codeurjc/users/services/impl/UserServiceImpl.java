package es.codeurjc.users.services.impl;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.Collection;
import java.util.stream.Collectors;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import es.codeurjc.users.dtos.requests.UpdateUserEmailRequestDto;
import es.codeurjc.users.dtos.requests.UserRequestDto;
import es.codeurjc.users.dtos.responses.UserResponseDto;
import es.codeurjc.users.exceptions.UserCanNotBeDeletedException;
import es.codeurjc.users.exceptions.UserNotFoundException;
import es.codeurjc.users.exceptions.UserWithSameNickException;
import es.codeurjc.users.models.User;
import es.codeurjc.users.repositories.UserRepository;
import es.codeurjc.users.restclients.CommentsRestClient;
import es.codeurjc.users.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private Mapper mapper;
    private UserRepository userRepository;
    private CommentsRestClient commentsRestClient;  
  
    public UserServiceImpl(Mapper mapper, UserRepository userRepository, CommentsRestClient commentsRestClient) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.commentsRestClient = commentsRestClient;
    }

    public Collection<UserResponseDto> findAll() {
        return this.userRepository.findAll().stream()
                .map(user -> this.mapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());
    }

    public UserResponseDto save(UserRequestDto userRequestDto) {
        if (this.userRepository.existsByNick(userRequestDto.getNick())) {
            throw new UserWithSameNickException();
        }
        User user = this.mapper.map(userRequestDto, User.class);
        user = this.userRepository.save(user);
        return this.mapper.map(user, UserResponseDto.class);
    }

    public UserResponseDto findById(long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return this.mapper.map(user, UserResponseDto.class);
    }

    public UserResponseDto findByNick(String nick) {
        User user = this.userRepository.findByNick(nick).orElseThrow(UserNotFoundException::new);
        return this.mapper.map(user, UserResponseDto.class);
    }

    public UserResponseDto updateEmail(long userId, UpdateUserEmailRequestDto updateUserEmailRequestDto) {
        User user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (!user.getEmail().equalsIgnoreCase(updateUserEmailRequestDto.getEmail())) {
            user.setEmail(updateUserEmailRequestDto.getEmail());
            user = this.userRepository.save(user);
        }
        return this.mapper.map(user, UserResponseDto.class);
    }

    public UserResponseDto delete(long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (!isEmpty(commentsRestClient.getComments(userId))) {
            throw new UserCanNotBeDeletedException();
        }
        this.userRepository.delete(user);
        return this.mapper.map(user, UserResponseDto.class);
    }

}
