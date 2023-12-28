package com.vinai.bookz.services;

import com.vinai.bookz.dtos.UserDTO;
import com.vinai.bookz.entities.User;
import com.vinai.bookz.exceptions.BadRequestException;
import com.vinai.bookz.exceptions.NotFoundException.UserNotFound;
import com.vinai.bookz.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO.UserData> getAllUsers() {
        return userRepository.findAll()
                .stream().map(User::toDTOData).toList();
    }

    public UserDTO.UserDetail getUser(Long id) {
        return findUser(id).toDTODetail();
    }

    public UserDTO.UserDetail createUser(UserDTO.UserCreate userDto) {

        if (checkEmailExists(userDto.getEmail()))
            throw new BadRequestException.EmailAlreadyExists();

        User user = new User(userDto.getEmail(), userDto.getName(), userDto.getSurname());
        return userRepository.save(user).toDTODetail();
    }

    public UserDTO.UserDetail updateUser(Long id, UserDTO.UserUpdate userDto) {
        User user = findUser(id);
        user.update(userDto.getName(), userDto.getSurname());
        return userRepository.save(user).toDTODetail();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    public User findUser(Long id) throws UserNotFound {
        return userRepository.findById(id)
                .orElseThrow(UserNotFound::new);
    }

    public Boolean checkEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }


}
