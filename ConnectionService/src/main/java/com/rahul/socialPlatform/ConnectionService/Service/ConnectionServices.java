package com.rahul.socialPlatform.ConnectionService.Service;

import com.rahul.socialPlatform.ConnectionService.Auth.UserContextHolder;
import com.rahul.socialPlatform.ConnectionService.Entity.Person;
import com.rahul.socialPlatform.ConnectionService.Repo.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectionServices {

    private final PersonRepository personRepository;

    public List<Person> getFirstDegreeConnections(){

        Long userId = UserContextHolder.getCurrentUserId();

        log.info("Getting first degree connections for userId : {}" , userId);

        return personRepository.getFirstDegreeConnections(userId);
    }

}
