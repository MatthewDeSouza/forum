package com.github.matthewdesouza.forum.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Role {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", unique = true)
    String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    Set<User> users;
}
