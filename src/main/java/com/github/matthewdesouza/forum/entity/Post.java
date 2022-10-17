package com.github.matthewdesouza.forum.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"user", "thread", "edited", "content", "comments"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @CreationTimestamp
    @Column(name = "creation", nullable = false, insertable = false, updatable = false)
    LocalDateTime creation;

    @UpdateTimestamp
    @Column(name = "edited")
    LocalDateTime edited;

    @Column(name = "title", nullable = false)
    String title;

    @Lob
    @Column(name = "content", nullable = false)
    String content;

    @ManyToOne
    User user;

    @ManyToOne
    Thread thread;

    @OneToMany
    Set<Comment> comments;
}