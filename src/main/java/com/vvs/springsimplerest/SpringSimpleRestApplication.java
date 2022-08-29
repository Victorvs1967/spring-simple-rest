package com.vvs.springsimplerest;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.r2dbc.core.DatabaseClient;

import com.vvs.springsimplerest.model.User;
import com.vvs.springsimplerest.repository.UserRepository;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringSimpleRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSimpleRestApplication.class, args);
	}

	@Bean
	ApplicationRunner init(UserRepository userRepository, DatabaseClient client) throws Exception {
		return args -> {
			client.sql("create table IF NOT EXISTS users (id SERIAL PRIMARY KEY, username varchar(255) not null unique, password varchar(128) not null, email varchar(255) not null unique);").fetch().first().subscribe();
			client.sql("DELETE FROM users;").fetch().first().subscribe();

			userRepository.saveAll(Flux.just(
				User.builder()
					.username("user")
					.password("password")
					.email("user@mail.me")
				.build(),
				User.builder()
					.username("victor")
					.password("victor")
					.email("victor@mail.me")
				.build()
			))
			.then()
			.subscribe();
		};
	}

}
