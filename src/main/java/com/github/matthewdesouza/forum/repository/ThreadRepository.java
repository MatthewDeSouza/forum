package com.github.matthewdesouza.forum.repository;

import com.github.matthewdesouza.forum.entity.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long> {
    Thread findThreadByName(String name);
    Thread findThreadById(Long id);
}
