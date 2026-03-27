package org.example.userController;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.dto.Request;
import org.example.dto.UserDTO;
import org.example.userService.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users/")
@Tag(name = "User API", description = "API для управления пользователями")
public class Controller {
    UserService service;
    public Controller(UserService service){
        this.service =  service;
    }

    @Operation(summary = "Создание пользователя", description = "Создает нового пользователя")
    @PostMapping
    public ResponseEntity<UserDTO> createUser(
            @Valid @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные для создания пользователя",
                    required = true,
                    content = @Content(examples = {
                            @ExampleObject(name = "Пример пользователя",
                                    value = "{name: 'Иванов Иван', email: 'ivivan@mail.com',age: 30}")
                    })
            )
            Request request){
        UserDTO dto = UserDTO.builder()
                .name(request.getName())
                .email(request.getEmail())
                .age(request.getAge()).build();
        return ResponseEntity.ok(service.createUser(dto));
    }


    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("test");
    }

    @Operation(summary = "Получение пользователя", description = "Ищет пользователя по id")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserByid(@PathVariable @Parameter(description = "ID пользователя", example = "1") long id){
        System.out.println(1);
        UserDTO user = service.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Получение всех пользователей", description = "Возвращает всех пользователей")
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(service.getAllUsers());
    }

    @Operation(summary = "Обновление пользователя", description = "Обновляет пользователя")
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable @Parameter(description = "ID пользователя", example = "1") long id, @RequestBody
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Данные для создания пользователя",
            required = true,
            content = @Content(examples = {
                    @ExampleObject(name = "Пример пользователя",
                            value = "{name: 'Иванов Иван', email: 'ivivan@mail.com',age: 30}")
            })
    )
    Request request){
        UserDTO dto = UserDTO.builder()
                .id(id)
                .name(request.getName())
                .email(request.getEmail())
                .age(request.getAge()).build();
        return ResponseEntity.ok(service.updateUser(dto));
    }

    @Operation(summary = "Удаление пользователя", description = "Удаляет пользователя")
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable @Parameter(description = "ID пользователя", example = "1") long id){
        return ResponseEntity.ok(service.deleteUser(id));
    }
}
