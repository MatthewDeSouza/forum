package com.github.matthewdesouza.forum.service;

import com.github.matthewdesouza.forum.entity.Post;
import com.github.matthewdesouza.forum.entity.Thread;
import com.github.matthewdesouza.forum.entity.User;

import java.util.Set;

public interface PostService {
    void savePost(Post post);
    Post getPostById(Long id);
    Set<Post> getPostsByUser(User user);
    Set<Post> getPostsByThread(Thread thread);
    void updatePost(Post post);
    void deletePost(Post post);
    void deletePostById(Long id);
}
