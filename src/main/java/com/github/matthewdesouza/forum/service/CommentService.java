package com.github.matthewdesouza.forum.service;

import com.github.matthewdesouza.forum.entity.Comment;
import com.github.matthewdesouza.forum.entity.Post;
import com.github.matthewdesouza.forum.entity.User;
import lombok.NonNull;

import java.util.Set;

public interface CommentService {
    void saveComment(Comment comment);
    Comment getCommentById(@NonNull Long id);
    Set<Comment> getCommentsByUser(User user);
    // TODO: Add custom query
    Set<Comment> getCommentsContainingContent(@NonNull String content);
    Set<Comment> getCommentsByPost(Post post);
    void deleteComment(Comment comment);
}
