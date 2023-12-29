package com.vinai.bookz.entities;


import com.vinai.bookz.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@SQLRestriction("deleted <> true")
@SQLDelete(sql = "UPDATE user SET email = CONCAT(email, '_deleted_', current_timestamp), deleted = true, deleted_at = current_timestamp WHERE id = ?")
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
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
                id, name, surname, email, createdAt,(long) userBooks.size(),
                (long) userBooks.stream()
                        .mapToInt(UserBook::getTimes)
                        .sum()
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
                        .sum(),
                userBooks.stream()
                        .collect(Collectors.toMap(
                                userBook -> userBook.getBook().getIsbn(),
                                UserBook::getTimes
                        )));
    }

    public void update(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

}
