package com.xdlr.webflux.router;

import com.xdlr.webflux.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class AllRouters {

    @Bean
    RouterFunction<ServerResponse> userRouter(UserHandler handler) {

        return RouterFunctions.nest(
                RequestPredicates.path("/userRouter"),
                RouterFunctions
                        .route(RequestPredicates.GET("/"), handler::getAllUser)
                        // 创建用户
                        .andRoute(RequestPredicates.POST("/")
                                        .and(accept(MediaType.APPLICATION_JSON_UTF8)),
                                handler::createUser)
                        // 删除用户
                        .andRoute(RequestPredicates.DELETE("/{id}"), handler::deleteUserById)
        );
    }

}
