package es.codeurjc.users.services;

import java.util.Collection;

import es.codeurjc.users.dtos.requests.UpdateUserEmailRequestDto;
import es.codeurjc.users.dtos.requests.UserRequestDto;
import es.codeurjc.users.dtos.responses.UserResponseDto;

public interface UserService {

    Collection<UserResponseDto> findAll();

    UserResponseDto save(UserRequestDto userRequestDto);

    UserResponseDto findById(long userId);

    UserResponseDto findByNick(String nick);

    UserResponseDto updateEmail(long userId, UpdateUserEmailRequestDto updateUserEmailRequestDto);

    UserResponseDto delete(long userId);

}
