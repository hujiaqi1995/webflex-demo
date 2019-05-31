package com.xdlr.webflux.repository;

import com.xdlr.webflux.domain.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

   Flux<User> findByAgeBetween(int start, int end);

   @Query("{'age':{'$gte':20,'$lte':30}}")
   Flux<User> oldUser();

}
