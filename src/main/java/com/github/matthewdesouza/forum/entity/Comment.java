package com.github.matthewdesouza.forum.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"content", "user", "post"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Lob
    @Column(name = "content", nullable = false)
    String content;

    @ManyToOne
    User user;

    @ManyToOne
    Post post;
}
