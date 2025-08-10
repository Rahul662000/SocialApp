package com.rahul.socialPlatform.posts_service.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalException {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException exception){
        ApiError error = new ApiError(exception.getLocalizedMessage() , HttpStatus.NOT_FOUND) ;
        return new ResponseEntity<>(error , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadCredentialsException(BadRequestException exception){
        ApiError error = new ApiError(exception.getLocalizedMessage() , HttpStatus.BAD_REQUEST) ;
        return new ResponseEntity<>(error , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntimeException(RuntimeException exception){
        ApiError error = new ApiError(exception.getLocalizedMessage() , HttpStatus.INTERNAL_SERVER_ERROR) ;
        return new ResponseEntity<>(error , HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(AuthenticationException.class)
//    public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException e){
//        APIError apiError = APIError.builder()
//                .status(HttpStatus.UNAUTHORIZED)
//                .message(e.getMessage())
//                .build();
//
//        return buildErrorResponseEntity(apiError);
//    }
//
//    @ExceptionHandler(JwtException.class)
//    public ResponseEntity<ApiResponse<?>> handleJwtException(JwtException e){
//        APIError apiError = APIError.builder()
//                .status(HttpStatus.UNAUTHORIZED)
//                .message(e.getMessage())
//                .build();
//
//        return buildErrorResponseEntity(apiError);
//    }
//
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<ApiResponse<?>> handleAccessDeniedException(AccessDeniedException e){
//        APIError apiError = APIError.builder()
//                .status(HttpStatus.FORBIDDEN)
//                .message(e.getMessage())
//                .build();
//
//        return buildErrorResponseEntity(apiError);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception exception){
//        APIError error = APIError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(exception.getMessage()).build() ;
//        return buildErrorResponseEntity(error);
//    }
//
//    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(APIError error) {
//        return new ResponseEntity<>(new ApiResponse<>(error) , error.getStatus());
//    }

}
