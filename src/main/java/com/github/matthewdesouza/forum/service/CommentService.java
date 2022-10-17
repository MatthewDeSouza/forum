package com.github.matthewdesouza.forum.service;

import com.github.matthewdesouza.forum.entity.Comment;
import com.github.matthewdesouza.forum.entity.Post;
import com.github.matthewdesouza.forum.entity.User;
import com.github.matthewdesouza.forum.repository.CommentRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import java.util.Collections;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class CommentService implements LoggingComments {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * Saves a comment to database
     * @param comment The Comment to save.
     */
    public void saveComment(Comment comment) {
        infoLogEntitySavePending(log, comment.getId());
        try {
            commentRepository.save(comment);
            infoLogEntitySaveSuccess(log, comment.getId());
        } catch (EntityExistsException e) {
            errorLogEntityAlreadyExists(log, comment.getId());
        }
    }

    /**
     * Retrieve a comment by id from the database.
     * Returns null if not found.
     * @param id The id of the Comment to find.
     * @return {@link Comment}
     */
    public Comment getCommentById(@NonNull Long id) {
        infoLogEntityRetrievePending(log, id);
        try {
            Comment temp = commentRepository.findCommentById(id);
            infoLogEntityRetrieveSuccess(log, id);
            return temp;
        } catch (EntityNotFoundException e) {
            errorLogEntityNotFound(log, id);
            return null;
        }
    }

    /**
     * Retrieve all comments by a user from within the database.
     * Returns an empty set if not found.
     * @param user the User to find their set of comments.
     * @return {@link Set<Comment>}
     */
    public Set<Comment> getCommentsByUser(User user) {
        infoLogEntityRetrievePending(log, user.getId());
        try {
            Set<Comment> temp = commentRepository.findCommentsByUser(user);
            infoLogEntityRetrieveSuccess(log, user.getId());
            return temp;
        } catch (EntityNotFoundException e) {
            errorLogEntityNotFound(log, user.getId());
            return Collections.emptySet();
        }
    }

    /**
     * Queries the database to find comments containing a string.
     * Returns an empty set if not found.
     * @param content The String to find within comment content.
     * @return {@link Set<Comment>}
     */
    public Set<Comment> getCommentsContainingContent(@NonNull String content) {
        infoLogEntityRetrievePending(log, content);
        try {
            Set<Comment> temp = commentRepository.findCommentsByContent(content);
            infoLogEntityRetrieveSuccess(log, content);
            return temp;
        } catch (EntityNotFoundException e) {
            errorLogEntityNotFound(log, content);
            return Collections.emptySet();
        }
    }

    /**
     * Retrieves the set of comments for a supplied post.
     * Returns an empty set if not found.
     * @param post The post to find the comments belonging to it.
     * @return {@link Set<Comment>}
     */
    public Set<Comment> getCommentsByPost(Post post) {
        infoLogEntityRetrievePending(log, post.getId());
        try {
            Set<Comment> temp = commentRepository.findCommentsByPost(post);
            infoLogEntityRetrieveSuccess(log, post.getId());
            return temp;
        } catch (EntityNotFoundException e) {
            errorLogEntityNotFound(log, post.getId());
            return Collections.emptySet();
        }
    }

    /**
     * Deletes a given comment from the database.
     * @param comment The comment to delete.
     */
    public void deleteComment(Comment comment) {
        infoLogEntityDeletePending(log, comment.getId());
        try {
            commentRepository.deleteById(comment.getId());
            infoLogEntityDeleteSuccess(log, comment.getId());
        } catch (EntityNotFoundException e) {
            errorLogEntityNotFound(log, comment.getId());
        }
    }

}
