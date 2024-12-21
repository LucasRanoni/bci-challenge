package com.bci.service;

import com.bci.model.UserEntity;
import com.bci.repository.UserRepository;
import com.bci.rest.dto.User;
import com.bci.rest.dto.UserResponse;
import com.bci.rest.exception.EmailAlreadyExistsException;
import com.bci.rest.exception.EmailException;
import com.bci.rest.exception.PasswordException;
import com.bci.rest.exception.UserNotFoundException;
import com.bci.util.JwtUtil;
import com.bci.util.Validator;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final Validator validator;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(Validator validator, UserRepository userRepository, JwtUtil jwtUtil, ModelMapper modelMapper) {
        this.validator = validator;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
    }

    private final ModelMapper modelMapper;


    @Override
    public List<UserResponse> getUsers() {
        Type listType = new TypeToken<List<UserResponse>>() {}.getType();
        List<UserEntity> userEntities = userRepository.findAll();
        return modelMapper.map(userEntities, listType);
    }

    @Override
    @Transactional
    public UserResponse saveUser(User user) {
        validateData(user);
        UserEntity userEntity = new UserEntity();
        modelMapper.map(user, userEntity);
        String token = jwtUtil.getJWTToken(user.getName());
        savePhones(userEntity);
        userEntity.setToken(token);
        userRepository.save(userEntity);
        return modelMapper.map(userEntity, UserResponse.class);
    }

    @Override
    public UserResponse updateUser(User user, UUID userId) {
        validateData(user);
        Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
        UserEntity userEntity;

        if (optionalUserEntity.isPresent()) {
            userEntity = optionalUserEntity.get();
            modelMapper.map(user, userEntity);
        } else {
            String token = jwtUtil.getJWTToken(user.getName());
            userEntity = new UserEntity();
            modelMapper.map(user, userEntity);
            userEntity.setToken(token);
            savePhones(userEntity);
        }
        userRepository.save(userEntity);
        return modelMapper.map(userEntity, UserResponse.class);
    }

    @Override
    public void deleteUser(UUID userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (userEntity.isEmpty()) throw new UserNotFoundException("User with id : " + userId + " Not found");
        userRepository.deleteById(userId);
    }

    private void savePhones(UserEntity userEntity) {
        if (!CollectionUtils.isEmpty(userEntity.getPhones())) {
            userEntity.getPhones().forEach(phone -> phone.setUser(userEntity));
        }
    }
    private void validateData(User user) {
        isValidEmail(user.getEmail());
        isValidPassword(user.getPassword());
        existsEmail(user.getEmail());
    }
    private void existsEmail(String email){
       Optional<UserEntity> user = userRepository.findByEmail(email);
       if(user.isPresent()) throw new EmailAlreadyExistsException("Email already exists");
    }
    private void isValidEmail(String email) {
        if(!validator.isValidEmail(email)) throw new EmailException("Email format is invalid");
    }

    private void isValidPassword(String password) {
        if(!validator.isValidPassword(password)) throw new PasswordException("Password should: have eight characters or more, include a capital letter, use at least one lowercase letter, " +
                        " consists of at least one digit and need to have one special symbol (i.e., @, #, $, %, etc.)");
    }
}
