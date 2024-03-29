package es.codeurjc.users.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.users.dtos.requests.UpdateUserEmailRequestDto;
import es.codeurjc.users.dtos.requests.UserRequestDto;
import es.codeurjc.users.dtos.responses.UserResponseDto;
import es.codeurjc.users.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
     }

     @Operation(summary = "Get all users")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found all users",
          content = {@Content(mediaType = "application/json",
              array = @ArraySchema(schema = @Schema(implementation = UserResponseDto.class)))})})
  @GetMapping("/")
  public Collection<UserResponseDto> getUsers() {
    return this.userService.findAll();
  }

  @Operation(summary = "Get a user by its id or nick")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found the user",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = UserResponseDto.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid format id or nick supplied",
          content = @Content),
      @ApiResponse(responseCode = "404", description = "User not found",
          content = @Content)})
  @GetMapping("/{user}")
  public UserResponseDto getUser(@Parameter(description = "id or nick of user to be searched")
                                 @PathVariable String user) {
    if (NumberUtils.isParsable(user)) {
      return this.userService.findById(Long.parseLong(user));
    } else {
      return this.userService.findByNick(user);
    }
  }

  @Operation(summary = "Create a new user")
  @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User to be created", required = true,
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = UserRequestDto.class)))
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Created the user",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = UserResponseDto.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid user attributes supplied",
          content = @Content),
      @ApiResponse(responseCode = "409", description = "Already exists an user with same nick",
          content = @Content)})
  @PostMapping("/")
  public UserResponseDto createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
    return this.userService.save(userRequestDto);
  }

  @Operation(summary = "Updates user's email")
  @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Email to be updated", required = true,
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = UpdateUserEmailRequestDto.class)))
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User with updated email",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = UserResponseDto.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid email supplied",
          content = @Content),
      @ApiResponse(responseCode = "404", description = "User not found",
          content = @Content)})
  @PatchMapping("/{userId}")
  public UserResponseDto updateUserEmail(@Parameter(description = "id of user to update the email")
                                         @PathVariable long userId,
                                         @Valid @RequestBody UpdateUserEmailRequestDto updateUserEmailRequestDto) {
    return this.userService.updateEmail(userId, updateUserEmailRequestDto);
  }

  @Operation(summary = "Deletes user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User deleted",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = UserResponseDto.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid format id supplied",
          content = @Content),
      @ApiResponse(responseCode = "404", description = "User not found",
          content = @Content),
      @ApiResponse(responseCode = "409", description = "User can't be deleted because has associated comments",
          content = @Content)})
  @DeleteMapping("/{userId}")
  public UserResponseDto deleteUser(@Parameter(description = "id of user to be deleted") @PathVariable long userId) {
    return this.userService.delete(userId);
  }

}
