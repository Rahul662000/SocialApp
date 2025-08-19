package com.rahul.socialPlatform.api_gateway;

import com.rahul.socialPlatform.api_gateway.Filters.AuthenticationFilters;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RouteConfig {

    private final AuthenticationFilters authenticationFilters;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("user-service", r -> r.path("/api/v1/users/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://USER-SERVICE"))
                .route("posts-service", r -> r.path("/api/v1/posts/**")
                        .filters(f -> f.stripPrefix(2)
                                .filter(authenticationFilters.apply(new AuthenticationFilters.Config())))
                        .uri("lb://POSTS-SERVICE"))
                .route("connection-service", r -> r.path("/api/v1/connections/**")
                        .filters(f -> f.stripPrefix(2)
                                .filter(authenticationFilters.apply(new AuthenticationFilters.Config())))
                        .uri("lb://CONNECTION-SERVICE"))
                .build();
    }

}
