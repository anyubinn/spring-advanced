package org.example.expert.domain.manager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.common.annotation.Auth;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.manager.dto.request.ManagerSaveRequest;
import org.example.expert.domain.manager.dto.response.ManagerResponse;
import org.example.expert.domain.manager.dto.response.ManagerSaveResponse;
import org.example.expert.domain.manager.service.ManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos/{todoId}/managers")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping
    public ResponseEntity<ManagerSaveResponse> saveManager(
            @Auth AuthUser authUser,
            @PathVariable long todoId,
            @Valid @RequestBody ManagerSaveRequest managerSaveRequest
    ) {

        ManagerSaveResponse managerSaveResponse = managerService.saveManager(authUser, todoId, managerSaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(managerSaveResponse);
    }

    @GetMapping
    public ResponseEntity<List<ManagerResponse>> getMembers(@PathVariable long todoId) {

        List<ManagerResponse> managersList = managerService.getManagers(todoId);

        return ResponseEntity.ok(managersList);
    }

    @DeleteMapping("/{managerId}")
    public ResponseEntity<Void> deleteManager(
            @Auth AuthUser authUser,
            @PathVariable long todoId,
            @PathVariable long managerId
    ) {

        managerService.deleteManager(authUser.getId(), todoId, managerId);

        return ResponseEntity.noContent().build();
    }
}
