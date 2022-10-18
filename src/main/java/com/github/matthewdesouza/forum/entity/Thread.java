package com.github.matthewdesouza.forum.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "posts")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Thread {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", unique = true)
    String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    Set<Post> posts;

    public void addPost(Post post) {
        if (posts == null) {
            posts = new HashSet<>();
        }
        posts.add(post);
        post.setThread(this);
    }
}
