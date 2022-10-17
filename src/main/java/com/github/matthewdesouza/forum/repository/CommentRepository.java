package com.github.matthewdesouza.forum.repository;

import com.github.matthewdesouza.forum.entity.Comment;
import com.github.matthewdesouza.forum.entity.Post;
import com.github.matthewdesouza.forum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.content LIKE %?1%")
    Set<Comment> findCommentsByContent(String content);
    Set<Comment> findCommentsByUser(User user);
    Comment findCommentById(Long id);
    Set<Comment> findCommentsByPost(Post post);
}
