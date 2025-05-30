package org.example.expert.domain.comment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.comment.dto.request.CommentSaveRequest;
import org.example.expert.domain.comment.dto.response.CommentResponse;
import org.example.expert.domain.comment.dto.response.CommentSaveResponse;
import org.example.expert.domain.comment.service.CommentService;
import org.example.expert.domain.common.annotation.Auth;
import org.example.expert.domain.common.dto.AuthUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos/{todoId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentSaveResponse> saveComment(
            @Auth AuthUser authUser,
            @PathVariable long todoId,
            @Valid @RequestBody CommentSaveRequest commentSaveRequest
    ) {

        CommentSaveResponse commentSaveResponse = commentService.saveComment(authUser, todoId, commentSaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(commentSaveResponse);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable long todoId) {

        List<CommentResponse> commentsList = commentService.getComments(todoId);

        return ResponseEntity.ok(commentsList);
    }
}
