package org.books.edgeservice.controller;

import org.books.edgeservice.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class.getName());
    @GetMapping("/user")
    public Mono<User> getUser(
            @AuthenticationPrincipal
            OidcUser oidcUser
    ){
        return Mono.just(new User(
                oidcUser.getPreferredUsername(),
                oidcUser.getGivenName(),
                oidcUser.getFamilyName(),
                List.of("employee", "customer")
        ));
    }
}
