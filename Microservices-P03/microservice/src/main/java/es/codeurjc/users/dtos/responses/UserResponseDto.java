package es.codeurjc.users.dtos.responses;

import lombok.Data;

@Data
public class UserResponseDto {

    private Long id;
    private String nick;
    private String email;

}
