package com.vvs.springsimplerest.service;

import com.vvs.springsimplerest.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
  public Flux<User> getUsers();
  public Mono<User> getUser(String username);
  public Mono<User> addUser(User user);
  public Mono<User> editUser(String username, User user);
  public Mono<Void> deleteUser(String username);
}
