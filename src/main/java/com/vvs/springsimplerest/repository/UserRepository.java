package com.vvs.springsimplerest.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.vvs.springsimplerest.model.User;

import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, Integer> {
  public Mono<User> findUserByUsername(String username);
}
