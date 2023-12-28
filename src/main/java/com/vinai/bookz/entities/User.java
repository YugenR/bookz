package com.vinai.bookz.entities;


import com.vinai.bookz.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@SQLRestriction("deleted <> true")
@SQLDelete(sql = "UPDATE user SET deleted = true, deleted_at = current_timestamp WHERE id = ?")
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime deletedAt;
    private boolean deleted;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<UserBook> userBooks = new HashSet<>();

    public User(String email, String name, String surname) {
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    public UserDTO.UserData toDTOData() {
        return new UserDTO.UserData(
                id, name, surname, email, createdAt
        );
    }

    public UserDTO.UserDetail toDTODetail() {
        return new UserDTO.UserDetail(
                id,
                name,
                surname,
                email,
                createdAt,
                (long) userBooks.size(),
                (long) userBooks.stream()
                        .mapToInt(UserBook::getTimes)
                        .sum());
    }

    public void update(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

}
