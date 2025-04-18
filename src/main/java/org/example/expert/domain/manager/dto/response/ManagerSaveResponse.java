package org.example.expert.domain.manager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.expert.domain.manager.entity.Manager;
import org.example.expert.domain.user.dto.response.UserResponse;

@Getter
@AllArgsConstructor
public class ManagerSaveResponse {

    private final Long id;
    private final UserResponse user;

    public static ManagerSaveResponse of(Manager manager, UserResponse user) {

        return new ManagerSaveResponse(manager.getId(), user);
    }
}
