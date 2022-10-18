package com.github.matthewdesouza.forum.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"content", "user", "thread", "comments"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Post {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @CreationTimestamp
    @Column(name = "creation", nullable = false, insertable = false, updatable = false)
    LocalDateTime creation;

    @Column(name = "title", nullable = false)
    String title;

    @Lob
    @Column(name = "content", nullable = false)
    String content;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "thread_id")
    Thread thread;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "comment_id")
    Set<Comment> comments;

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setPost(this);
    }
}