package com.rahul.socialPlatform.posts_service.Exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String Message){
        super(Message);
    }

}
