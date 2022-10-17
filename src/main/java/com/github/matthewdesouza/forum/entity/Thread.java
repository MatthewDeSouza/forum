package com.github.matthewdesouza.forum.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "posts")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", unique = true)
    String name;

    @OneToMany
    Set<Post> posts;
}
