package com.github.matthewdesouza.forum.service;

import com.github.matthewdesouza.forum.entity.Thread;

import java.util.Set;

public interface ThreadService {
    Set<Thread> getAllThreads();
    void saveThread(Thread thread);
    Thread getThreadById(Long id);
    Thread getThreadByName(String name);
    void updateThread(Thread thread);
    void deleteThread(Thread thread);
}
