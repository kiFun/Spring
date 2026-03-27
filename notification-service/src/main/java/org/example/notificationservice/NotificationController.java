package org.example.notificationservice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
@Tag(name = "Notification API", description = "API для отправки email о создании и удалении пользователей")
public class NotificationController {
    final EmailService service;

    public NotificationController(EmailService service) {
        this.service = service;
    }

    @Operation(summary = "Создание пользователя", description = "Отправляет сообщение о создании пользователя")
    @PostMapping("/created")
    public ResponseEntity<Void> createdEmail(@RequestBody String to){
        service.sendCreated(to);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удаление пользователя", description = "Отправляет сообщение об удалении пользователя")
    @PostMapping("/deleted")
    public ResponseEntity<Void> deletedEmail(@RequestBody String to){
        service.sendDeleted(to);
        return ResponseEntity.ok().build();
    }
}
