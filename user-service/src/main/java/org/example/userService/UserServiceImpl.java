package org.example.userService;

import org.example.repository.User;
import org.example.UserNotFoundException;
import org.example.dto.UserDTO;
import org.example.repository.UserRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService{
    final UserRepository repository;
    final KafkaTemplate<String, UserEvent> kafkaTemplate;
    final String userEventTopic = "user-events";

    public UserServiceImpl(UserRepository repository, KafkaTemplate<String, UserEvent> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        kafkaTemplate.send(userEventTopic, UserEvent.created(userDTO.getEmail()));
        return repository.save(userDTO.toUser()).dto();
    }

    @Override
    public UserDTO getUserById(long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException("Пользователь с id "+ id +" не найден")).dto();
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(user -> user.dto())
                .toList();
    }

    @Override
    public UserDTO updateUser(UserDTO request) {
        User user = repository.findById(request.getId()).orElseThrow(() -> new UserNotFoundException("Пользователь с id "+ request.getId() +" не найден"));
        if(request.getName() != null && !request.getName().isEmpty() && request.getName().matches("[A-ZА-Я][a-zа-я]+\\s[A-ZА-Я][a-zа-я]+")) user.setName(request.getName());
        if(request.getEmail() != null && !request.getEmail().isEmpty() && request.getEmail().matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}")) user.setEmail(request.getEmail());
        if(request.getAge()>0) user.setAge(request.getAge());
        return repository.save(user).dto();
    }

    @Override
    public UserDTO deleteUser(long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException("Пользователь с id "+ id +" не найден"));
        kafkaTemplate.send(userEventTopic, UserEvent.deleted(user.getEmail()));
        repository.deleteUserById(id);
        return user.dto();
    }
}
