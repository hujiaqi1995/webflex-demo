package com.xdlr.webflux.controller;

import com.xdlr.webflux.domain.User;
import com.xdlr.webflux.repository.UserRepository;
import com.xdlr.webflux.util.CheckUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    // 推荐使用构造函数注入，而不是使用@Autowried

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public Flux<User> getAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/stream/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamGetAll() {
        return repository.findAll();
    }

    @PostMapping("/")
    public Mono<User> createUser(@Valid @RequestBody User user) {
        CheckUtil.checkName(user.getName());
        return this.repository.save(user);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable("id") String id) {
        return this.repository.findById(id)
                // 如果要操作数据，并返回一个Mono，这个时候使用flatMap
                // 如果不操作数据，只是转换数据，用map
                .flatMap(user -> this.repository.delete(user)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<User>> updateUser(@PathVariable("id") String id,
                                                 @Valid @RequestBody User user) {
        CheckUtil.checkName(user.getName());
        return this.repository.findById(id)
                .flatMap(u -> {
                    u.setAge(user.getAge());
                    u.setName(user.getName());
                    return this.repository.save(u);
                })
                .map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> findUserById(@PathVariable("id") String id) {
        return this.repository.findById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/age/{start}/{end}")
    public Flux<User> FindByAge(@PathVariable("start") int start, @PathVariable("end") int end) {
        return this.repository.findByAgeBetween(start, end);
    }

    @GetMapping(value = "/stream/age/{start}/{end}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamFindByAge(@PathVariable("start") int start, @PathVariable("end") int end) {
        return this.repository.findByAgeBetween(start, end);
    }

    @GetMapping(value = "/age/old")
    public Flux<User> findOldUser() {
        return this.repository.oldUser();
    }

    @GetMapping(value = "/stream/age/old", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamFindOldUser() {
        return this.repository.oldUser();
    }

}
