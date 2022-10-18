package com.github.matthewdesouza.forum.service.impl;

import com.github.matthewdesouza.forum.entity.Post;
import com.github.matthewdesouza.forum.entity.Thread;
import com.github.matthewdesouza.forum.entity.User;
import com.github.matthewdesouza.forum.repository.PostRepository;
import com.github.matthewdesouza.forum.service.PostService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

@Slf4j
@Service
@Transactional
public class PostServiceImpl implements PostService, LoggingComments {
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * Saves a post to the database.
     * @param post Post to save to database.
     */
    @Override
    public void savePost(Post post) {
////        infoLogEntitySavePending(log, post.getId());
//        try {
//            postRepository.save(post);
////            infoLogEntitySaveSuccess(log, post.getId());
//        } catch (EntityExistsException e) {
////            errorLogEntityAlreadyExists(log, post.getId());
//        }
        postRepository.save(post);
    }

    /**
     * Retrieves a post by id from the database.
     * @param id The id of the Post
     * @return {@link Post}
     */
    @Override
    public Post getPostById(Long id) {
        infoLogEntityRetrievePending(log, id);
        try {
            Post temp = postRepository.getReferenceById(id);
            infoLogEntityRetrieveSuccess(log, id);
            return temp;
        } catch (EntityNotFoundException e) {
            errorLogEntityNotFound(log, id);
            return null;
        }
    }

    /**
     * Queries the database to find posts by a supplied user.
     * Returns an empty set if user does not exist.
     * @param user User object to retrieve posts.
     * @return {@link Set<Post>}
     */
    @Override
    public Set<Post> getPostsByUser(User user) {
        infoLogEntityRetrievePending(log, user.getId());
        try {
            Set<Post> temp = postRepository.findPostByUser(user);
            infoLogEntityRetrieveSuccess(log, user.getId());
            return temp;
        } catch (EntityNotFoundException e) {
            errorLogEntityNotFound(log, user.getId());
            return Collections.emptySet();
        }
    }

    /**
     * Queries the database to find posts within a supplied thread.
     * Returns an empty set if thread has no posts.
     * @param thread Thread object to retrieve posts.
     * @return {@link Set<Post>}
     */
    @Override
    public Set<Post> getPostsByThread(Thread thread) {
        infoLogEntityRetrievePending(log, thread.getName());
        try {
            Set<Post> temp = postRepository.findPostByThread(thread);
            infoLogEntityRetrieveSuccess(log, thread.getName());
            return temp;
        } catch (EntityNotFoundException e) {
            errorLogEntityNotFound(log, thread.getName());
            return Collections.emptySet();
        }
    }

    /**
     * Updates the supplied object within the database.
     * Generates a {@link LocalDateTime} to signify when entity was last modified.
     * @param post Post object to update
     */
    @Override
    public void updatePost(Post post) {
//        infoLogEntityUpdatePending(log, post.getId());
        try {
            Post temp = postRepository.getReferenceById(post.getId());
            temp.setTitle(post.getTitle());
            temp.setUser(post.getUser());
            temp.setComments(post.getComments());
            temp.setContent(post.getContent());
            temp.setThread(post.getThread());
            postRepository.save(temp);
//            infoLogEntityUpdateSuccess(log, temp.getId());
        } catch (EntityNotFoundException e) {
//            errorLogEntityNotFound(log, post.getId());
        }
    }

    /**
     * Deletes supplied Post from the database.
     * @param post Post object to be deleted.
     */
    @Override
    public void deletePost(Post post) {
//        infoLogEntityDeletePending(log, post.getId());
        try {
            postRepository.delete(post);
//            infoLogEntityDeleteSuccess(log, post.getId());
        } catch (EntityNotFoundException e) {
//            errorLogEntityNotFound(log, post.getId());
        }
    }

    /**
     * Deletes entry with supplied id from the database.
     * @param id Post object primary key to be deleted.
     */
    @Override
    public void deletePostById(Long id) {
        infoLogEntityDeletePending(log, id);
        try {
            postRepository.deleteById(id);
            infoLogEntityDeleteSuccess(log, id);
        } catch (EntityNotFoundException e) {
            errorLogEntityNotFound(log, id);
        }
    }
}
