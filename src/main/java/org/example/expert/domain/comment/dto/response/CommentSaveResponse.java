package org.example.expert.domain.comment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.expert.domain.comment.entity.Comment;
import org.example.expert.domain.user.dto.response.UserResponse;

@Getter
@AllArgsConstructor
public class CommentSaveResponse {

    private final Long id;
    private final String contents;
    private final UserResponse user;

    public static CommentSaveResponse of(Comment comment, UserResponse user) {

        return new CommentSaveResponse(comment.getId(), comment.getContents(), user);
    }
}
