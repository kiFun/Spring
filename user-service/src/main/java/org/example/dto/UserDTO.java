package org.example.dto;

import lombok.*;
import org.example.repository.User;
import org.example.userController.Controller;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends RepresentationModel<UserDTO> {
    long id;
    String name;
    String email;
    int age;
    LocalDateTime created_at;

    public UserDTO addSelfLink() {
        add(linkTo(methodOn(Controller.class).getUserByid(id)).withSelfRel());
        return this;
    }

    public UserDTO addUpdateLink() {
        add(linkTo(methodOn(Controller.class).updateUser(id, null))
                .withRel("update")
                .withType("PUT"));
        return this;
    }

    public UserDTO addDeleteLink() {
        add(linkTo(methodOn(Controller.class).deleteUser(id))
                .withRel("delete")
                .withType("DELETE"));
        return this;
    }

    public User toUser(){
        return User.builder()
                .id(id)
                .name(name)
                .email(email)
                .age(age)
                .created_at(created_at)
                .build();
    }
}
