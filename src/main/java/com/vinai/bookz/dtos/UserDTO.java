package com.vinai.bookz.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.Map;

public class UserDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserCreate {
        @NonNull
        private String name;
        @NonNull
        private String surname;
        @NonNull
        private String email;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserUpdate {
        @NonNull
        private String name;
        @NonNull
        private String surname;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserData {
        private Long id;
        private String name;
        private String surname;
        private String email;
        private LocalDateTime createdAt;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserDetail {
        private Long id;
        private String name;
        private String surname;
        private String email;
        private LocalDateTime createdAt;
        // How many books the user has read
        private Long bookCount;
        // Sum of every time the user has read
        private Long totalReadCount;
        private Map<String, Integer> books;
    }

}
