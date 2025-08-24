package org.books.edgeservice.controller;

import org.books.edgeservice.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import java.util.logging.Logger;

@RestController
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @GetMapping("/user")
    public Mono<User> getUser(
            @AuthenticationPrincipal OidcUser oidcUser,
            @RegisteredOAuth2AuthorizedClient("keycloak") OAuth2AuthorizedClient authorizedClient
            ){
        logger.info("ID Token: " + oidcUser.getIdToken().getTokenValue());
        logger.info("Access Token: " + authorizedClient.getAccessToken().getTokenValue());
        return Mono.just(new User(
                oidcUser.getPreferredUsername(),
                oidcUser.getGivenName(),
                oidcUser.getFamilyName(),
                oidcUser.getClaimAsStringList("roles")
        ));
    }
}
