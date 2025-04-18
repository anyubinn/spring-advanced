package org.example.expert.domain.todo.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.user.dto.response.UserResponse;

import java.time.LocalDateTime;
import org.example.expert.domain.user.entity.User;

@Getter
@Builder
public class TodoResponse {

    private final Long id;
    private final String title;
    private final String contents;
    private final String weather;
    private final UserResponse user;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public static TodoResponse of(Todo todo) {

        return TodoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .contents(todo.getContents())
                .weather(todo.getWeather())
                .user(UserResponse.of(todo.getUser()))
                .createdAt(todo.getCreatedAt())
                .modifiedAt(todo.getModifiedAt())
                .build();
    }
}
