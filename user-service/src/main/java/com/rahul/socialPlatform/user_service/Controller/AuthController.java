package com.rahul.socialPlatform.user_service.Controller;

import com.rahul.socialPlatform.user_service.Dto.LogInRequestDto;
import com.rahul.socialPlatform.user_service.Dto.SignUpRequestDto;
import com.rahul.socialPlatform.user_service.Dto.UserDto;
import com.rahul.socialPlatform.user_service.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto){

        UserDto userDto = authService.signUp(signUpRequestDto);
        return new ResponseEntity<>(userDto , HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody LogInRequestDto logInRequestDto){

        String token = authService.logIn(logInRequestDto);
        return ResponseEntity.ok(token);

    }

}
