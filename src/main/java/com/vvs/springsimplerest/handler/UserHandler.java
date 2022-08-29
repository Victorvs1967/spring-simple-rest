package com.vvs.springsimplerest.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.vvs.springsimplerest.model.User;
import com.vvs.springsimplerest.service.UserService;

import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class UserHandler {
  
  @Autowired
  private UserService userService;

  public Mono<ServerResponse> getAllUsers(ServerRequest request) {
    return ServerResponse
      .ok()
      .contentType(APPLICATION_JSON)
      .body(userService.getUsers(), User.class);
  }

  public Mono<ServerResponse> getUser(ServerRequest request) {
    return ServerResponse
      .ok()
      .contentType(APPLICATION_JSON)
      .body(userService.getUser(request.pathVariable("user")), User.class);
  }

  public Mono<ServerResponse> addUser(ServerRequest request) {
    return request.bodyToMono(User.class)
      .map(userService::addUser)
      .flatMap(user -> ServerResponse
        .ok()
        .contentType(APPLICATION_JSON)
        .body(user, User.class));
  }

  public Mono<ServerResponse> editUser(ServerRequest request) {
    String username = request.pathVariable("user");
    return request.bodyToMono(User.class)
      .map(user -> userService.editUser(username, user))
      .flatMap(user -> ServerResponse
        .ok()
        .contentType(APPLICATION_JSON)
        .body(user, User.class));
  }

  public Mono<ServerResponse> deleteUser(ServerRequest request) {
    return ServerResponse
      .ok()
      .contentType(APPLICATION_JSON)
      .body(userService.deleteUser(request.pathVariable("user")), Void.class);
  }
}
