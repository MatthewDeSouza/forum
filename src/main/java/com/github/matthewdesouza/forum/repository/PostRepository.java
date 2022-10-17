package com.github.matthewdesouza.forum.repository;

import com.github.matthewdesouza.forum.entity.Post;
import com.github.matthewdesouza.forum.entity.Thread;
import com.github.matthewdesouza.forum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Set<Post> findPostByCreation(LocalDateTime dateTime);
    Set<Post> findPostByTitle(String title);
    Set<Post> findPostByContent(String content);
    Set<Post> findPostByUser(User user);
    Set<Post> findPostByThread(Thread thread);
}
