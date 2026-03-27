package org.example.notificationservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEvent {
    public enum Type{
        CREATE, DELETE;
    }
    Type type;
    String email;
    LocalDateTime timestamp;

    public static UserEvent created(String email){
        return new UserEvent(Type.CREATE, email, LocalDateTime.now());
    }

    public static UserEvent deleted(String email){
        return new UserEvent(Type.DELETE, email, LocalDateTime.now());
    }
}
