package com.vvs.springsimplerest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vvs.springsimplerest.model.User;
import com.vvs.springsimplerest.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public Flux<User> getUsers() {
    return userRepository.findAll();
  }

  @Override
  public Mono<User> getUser(String username) {
    return userRepository.findUserByUsername(username);
  }

  @Override
  public Mono<User> addUser(User user) {
    return userRepository.findUserByUsername(user.getUsername())
      .switchIfEmpty(userRepository.save(User
        .builder()
          .username(user.getUsername())
          .password(user.getPassword())
          .email(user.getEmail())
        .build()));
  }

  @Override
  public Mono<User> editUser(String username, User newUser) {
    return userRepository.findUserByUsername(username)
      .map(user -> User
        .builder()
          .id(user.getId())
          .username(newUser.getUsername())
          .password(newUser.getPassword())
          .email(newUser.getEmail())
        .build())
      .map(isUser -> isUser)
      .flatMap(userRepository::save);
  }

  @Override
  public Mono<Void> deleteUser(String username) {
    return userRepository.findUserByUsername(username)
      .map(userRepository::delete)
      .cast(Void.class);
  }
}
