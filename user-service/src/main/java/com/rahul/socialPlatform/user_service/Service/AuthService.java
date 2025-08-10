package com.rahul.socialPlatform.user_service.Service;

import com.rahul.socialPlatform.user_service.Dto.LogInRequestDto;
import com.rahul.socialPlatform.user_service.Dto.SignUpRequestDto;
import com.rahul.socialPlatform.user_service.Dto.UserDto;
import com.rahul.socialPlatform.user_service.Entity.User;
import com.rahul.socialPlatform.user_service.Exception.BadRequestException;
import com.rahul.socialPlatform.user_service.Exception.ResourceNotFoundException;
import com.rahul.socialPlatform.user_service.Repo.UserRepository;
import com.rahul.socialPlatform.user_service.Utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final JwtService jwtService;

    public UserDto signUp(SignUpRequestDto signUpRequestDto) {

        boolean exists = userRepository.existsByEmail(signUpRequestDto.getEmail());

        if(exists){
            throw new BadRequestException("User already exists , cannot signup again.");
        }

        User user = mapper.map(signUpRequestDto , User.class);
        user.setPassword(PasswordUtils.hashPassword(signUpRequestDto.getPassword()));

        User savedUser = userRepository.save(user);
        return mapper.map(savedUser , UserDto.class);

    }

    public String logIn(LogInRequestDto logInRequestDto) {

        User user = userRepository.findByEmail(logInRequestDto.getEmail())
                    .orElseThrow(() -> new ResourceNotFoundException("User is not Found with Email : " + logInRequestDto.getEmail()));
        boolean isPasswordMatch = PasswordUtils.checkPassword(logInRequestDto.getPassword() , user.getPassword());
        if(!isPasswordMatch){
            throw new BadRequestException("Incorrect Password");
        }
        return jwtService.generateAccessToken(user);

    }
}
