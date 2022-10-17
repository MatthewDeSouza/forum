package com.github.matthewdesouza.forum.repository;

import com.github.matthewdesouza.forum.entity.Post;
import com.github.matthewdesouza.forum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Set<Post> findPostByUser(User user);
}
