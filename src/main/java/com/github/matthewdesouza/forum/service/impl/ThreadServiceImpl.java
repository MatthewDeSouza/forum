package com.github.matthewdesouza.forum.service.impl;

import com.github.matthewdesouza.forum.repository.ThreadRepository;
import com.github.matthewdesouza.forum.entity.Thread;
import com.github.matthewdesouza.forum.service.ThreadService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class ThreadServiceImpl implements ThreadService, LoggingComments {
    private final ThreadRepository threadRepository;

    @Autowired
    public ThreadServiceImpl(ThreadRepository threadRepository) {
        this.threadRepository = threadRepository;
    }

    /**
     * Retrieves all Thread entries in the database.
     * @return {@link Set<Thread>}
     */
    @Override
    public Set<Thread> getAllThreads() {
        infoLogEntityRetrievePending(log);
        Set<Thread> temp = new HashSet<>(threadRepository.findAll());
        infoLogEntityRetrieveSuccess(log);
        return temp;
    }

    /**
     * Saves a thread to the database.
     * @param thread Thread to be saved to the database.
     */
    @Override
    public void saveThread(Thread thread) {
        infoLogEntitySavePending(log, thread.getId());
        try {
            threadRepository.save(thread);
            infoLogEntitySaveSuccess(log, thread.getId());
        } catch (EntityExistsException e) {
            errorLogEntityAlreadyExists(log, thread.getId());
        }
    }

    /**
     * Queries database to return Thread given the supplied id.
     * Returns null if not found.
     * @param id The id of the requested entry.
     * @return {@link Thread}
     */
    @Override
    public Thread getThreadById(Long id) {
        infoLogEntityRetrievePending(log, id);
        try {
            Thread temp = threadRepository.findThreadById(id);
            infoLogEntityRetrieveSuccess(log, id);
            return temp;
        } catch (EntityNotFoundException e) {
            errorLogEntityNotFound(log, id);
            return null;
        }
    }

    /**
     * Queries database to return Thread given the supplied name.
     * Returns null if not found.
     * @param name The name of the requested entry.
     * @return {@link Thread}
     */
    @Override
    public Thread getThreadByName(String name) {
        infoLogEntityRetrievePending(log, name);
        try {
            Thread temp = threadRepository.findThreadByName(name);
            infoLogEntityRetrieveSuccess(log, name);
            return temp;
        } catch (EntityNotFoundException e) {
            errorLogEntityNotFound(log, name);
            return null;
        }
    }

    /**
     * Updates the supplied Thread within the database.
     * @param thread Entry to be updated.
     */
    @Override
    public void updateThread(Thread thread) {
//        infoLogEntityUpdatePending(log, thread.getId());
        try {
//            Thread temp = threadRepository.getReferenceById(thread.getId());
            Thread temp = threadRepository.findThreadByName(thread.getName());
            temp.setName(thread.getName());
            temp.setPosts(thread.getPosts());
            threadRepository.save(temp);
//            infoLogEntityUpdateSuccess(log, temp.getId());
        } catch (EntityNotFoundException e) {
//            errorLogEntityNotFound(log, thread.getId());
        }
    }

    /**
     * Deletes the supplied Thread from the database.
     * @param thread Entry to be deleted.
     */
    @Override
    public void deleteThread(Thread thread) {
        infoLogEntityDeletePending(log, thread.getId());
        try {
            threadRepository.delete(thread);
            infoLogEntityDeleteSuccess(log, thread.getId());
        } catch (EntityNotFoundException e) {
            errorLogEntityNotFound(log, thread.getId());
        }
    }
}
