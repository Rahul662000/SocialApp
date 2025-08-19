package com.rahul.socialPlatform.ConnectionService.Controller;

import com.rahul.socialPlatform.ConnectionService.Entity.Person;
import com.rahul.socialPlatform.ConnectionService.Service.ConnectionServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class ConnectionControllers {

    private final ConnectionServices connectionServices;

    @GetMapping("/")
    public ResponseEntity<String> welcome(){
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("/first-degree")
    public ResponseEntity<List<Person>> getFirstConnections(){
        return ResponseEntity.ok(connectionServices.getFirstDegreeConnections());
    }

}
