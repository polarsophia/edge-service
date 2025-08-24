package org.books.edgeservice.domain;

import java.util.List;

public record User(
        String username,
        String firsName,
        String lastName,
        List<String> roles
) {
}
