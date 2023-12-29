package com.vinai.bookz.services;

import com.vinai.bookz.common.pagination.PageConverter;
import com.vinai.bookz.common.pagination.SortableEntities;
import com.vinai.bookz.common.pagination.SortableFields;
import com.vinai.bookz.dtos.PageConverterDTO;
import com.vinai.bookz.dtos.UserDTO;
import com.vinai.bookz.entities.User;
import com.vinai.bookz.exceptions.BadRequestException;
import com.vinai.bookz.exceptions.NotFoundException.UserNotFound;
import com.vinai.bookz.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public PageConverterDTO<UserDTO.UserData> getAllUsers(
            Integer page,
            Integer num,
            List<String> sort,
            String keyword
    ) {
        return PageConverter
                .toDTOPage(userRepository.findAll(
                        (page == null && num == null) ? null : PageRequest.of(
                                page != null ? page : SortableFields.DEFAULT_PAGE,
                                num != null ? num : SortableFields.DEFAULT_PAGE_DIM,
                                SortableFields.getSorter(SortableEntities.USERS, sort)
                        ), keyword
                ).map(User::toDTOData));
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
