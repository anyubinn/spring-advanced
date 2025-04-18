package org.example.expert.domain.manager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.expert.domain.manager.entity.Manager;
import org.example.expert.domain.user.dto.response.UserResponse;

@Getter
@AllArgsConstructor
public class ManagerResponse {

    private final Long id;
    private final UserResponse user;

    public static ManagerResponse of(Manager manager, UserResponse user) {

        return new ManagerResponse(manager.getId(), user);
    }
}
