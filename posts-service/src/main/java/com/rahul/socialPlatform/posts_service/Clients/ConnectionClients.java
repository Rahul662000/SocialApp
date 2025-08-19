package com.rahul.socialPlatform.posts_service.Clients;

import com.rahul.socialPlatform.posts_service.Dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "connection-service" , path = "/connections")
public interface ConnectionClients {

    @GetMapping("/core/first-degree")
    List<PersonDto> getFirstConnections();

}
