package org.example.repository;

import jakarta.persistence.*;
import lombok.*;
import org.example.dto.UserDTO;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    String email;
    int age;
    LocalDateTime created_at;

    @PrePersist
    public void onCreate(){
        created_at = LocalDateTime.now();
    }

    public UserDTO dto(){
        return UserDTO.builder().id(id)
                .name(name)
                .email(email)
                .age(age)
                .created_at(created_at)
                .build()
                .addSelfLink()
                .addUpdateLink()
                .addDeleteLink();
    }
}
