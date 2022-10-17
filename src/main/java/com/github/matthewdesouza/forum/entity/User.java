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
@EqualsAndHashCode(exclude = {"password", "posts", "comments", "role"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @CreationTimestamp
    @Column(name = "creation_date", nullable = false, updatable = false, insertable = false)
    LocalDateTime creationDate;

    @Column(name = "email", unique = true, nullable = false)
    String email;

    @Column(name = "username", unique = true, nullable = false)
    String username;

    @Column(name = "password", nullable = false)
    String password;

    @OneToMany(cascade = CascadeType.ALL)
    Set<Post> posts;

    @OneToMany(cascade = CascadeType.ALL)
    Set<Comment> comments;

    @ManyToOne(optional = false)
    Role role;
}
