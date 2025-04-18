package org.example.expert.domain.todo.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.user.dto.response.UserResponse;

@Getter
@Builder
public class TodoSaveResponse {

    private final Long id;
    private final String title;
    private final String contents;
    private final String weather;
    private final UserResponse user;

    public static TodoSaveResponse of(Todo todo, String weather) {

        return TodoSaveResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .contents(todo.getContents())
                .weather(weather)
                .user(new UserResponse(todo.getUser().getId(), todo.getUser().getEmail()))
                .build();
    }
}
