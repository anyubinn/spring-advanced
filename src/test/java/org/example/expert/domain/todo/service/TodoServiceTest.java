package org.example.expert.domain.todo.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.example.expert.client.WeatherClient;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.common.exception.InvalidRequestException;
import org.example.expert.domain.todo.dto.request.TodoSaveRequest;
import org.example.expert.domain.todo.dto.response.TodoSaveResponse;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.todo.repository.TodoRepository;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.enums.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private WeatherClient weatherClient;

    @InjectMocks
    private TodoService todoService;

    @Test
    void todo를_정상적으로_등록한다() {

        AuthUser authUser = AuthUser.of(1L, "email", UserRole.USER);
        User user = User.fromAuthUser(authUser);

        String weather = weatherClient.getTodayWeather();

        TodoSaveRequest todoSaveRequest = new TodoSaveRequest("title", "contents");
        Todo todo = Todo.of(todoSaveRequest, weather, user);

        given(todoRepository.save(any())).willReturn(todo);

        TodoSaveResponse result = todoService.saveTodo(authUser, todoSaveRequest);

        assertNotNull(result);
        assertEquals(todo.getTitle(), result.getTitle());
        assertEquals(todo.getUser().getId(), result.getUser().getId());
        assertEquals(todo.getContents(), result.getContents());
    }

    @Test
    void todo_존재하지_않는_todoId로_사용자_조회_시_예외_발생한다() {

        Long todoId = 1L;

        assertThatThrownBy(() -> todoService.getTodo(todoId)).isInstanceOf(InvalidRequestException.class)
                .hasMessage("Todo not found");
    }
}