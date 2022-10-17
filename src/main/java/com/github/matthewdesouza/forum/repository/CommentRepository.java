package com.github.matthewdesouza.forum.repository;

import com.github.matthewdesouza.forum.entity.Comment;
import com.github.matthewdesouza.forum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Set<Comment> findCommentByUser(User user);
    Set<Comment> findCommentByContent(String content);
}
