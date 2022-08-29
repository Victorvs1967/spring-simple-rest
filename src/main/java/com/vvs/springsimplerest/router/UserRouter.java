package com.vvs.springsimplerest.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.vvs.springsimplerest.handler.UserHandler;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class UserRouter {
  
  @Bean
  public RouterFunction<ServerResponse> userRoutingFunction(UserHandler handler) {
    return route()
      .nest(path("/api/users"), builder -> builder
        .GET("", handler::getAllUsers)
        .POST("", handler::addUser)
        .GET("/{user}", handler::getUser)
        .PUT("/{user}", handler::editUser)
        .DELETE("/{user}", handler::deleteUser))
      .build();
  }
}
