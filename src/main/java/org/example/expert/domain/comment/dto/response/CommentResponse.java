package org.example.expert.domain.comment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.expert.domain.comment.entity.Comment;
import org.example.expert.domain.user.dto.response.UserResponse;

@Getter
@AllArgsConstructor
public class CommentResponse {

    private final Long id;
    private final String contents;
    private final UserResponse user;

    public static CommentResponse of(Comment comment, UserResponse user) {

        return new CommentResponse(comment.getId(), comment.getContents(), user);
    }
}
