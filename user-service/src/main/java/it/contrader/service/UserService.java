package it.contrader.service;

import it.contrader.dao.UserRepository;
import it.contrader.dto.UserDTO;
import it.contrader.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final WebClient.Builder webClient;

    public List<UserDTO> getAll(){
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().map(this::mapToDTO).toList();
    }

    public UserDTO getById(int id){
        return this.mapToDTO(userRepository.findById(id));
    }

    public void insert(UserDTO userDTO){
        userRepository.save(User.builder()
                .dob(userDTO.getDob())
                .name(userDTO.getName())
                .id_login(userDTO.getId_login())
                .build());
    }

    public void update(UserDTO userDTO){
        userRepository.save(User.builder()
                .id(userDTO.getId())
                .dob(userDTO.getDob())
                .name(userDTO.getName())
                .id_login(userDTO.getId_login())
                .build());
    }

    public void delete(int id){
        userRepository.delete(userRepository.findById(id));
    }

    public String test(){
        return webClient.build().get()
                .uri("http://clothes-service/clothes/test")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public boolean existsUserById(int id){
        return userRepository.existsById(id);
    }

    private UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .dob(user.getDob())
                .name(user.getName())
                .id_login(user.getId_login())
                .build();
    }

}
