package com.substring.foodie.food.service.external;

import com.substring.foodie.food.config.AppConstants;
import com.substring.foodie.food.dto.RestaurantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class RestWebClientService {

    @Autowired
    private WebClient.Builder webClient;
    public RestaurantDto getById(String resId){
//        RestaurantDto restaurantDto = webClient.get().uri("/api/v1/restaurants/{id}", resId)
//                .retrieve()
//                .bodyToMono(RestaurantDto.class)
//                .block();
//
//        return restaurantDto;

        RestaurantDto restaurantDto = webClient.baseUrl(AppConstants.RESTAURANT_SERVICE_URL).build().get().uri("/api/v1/restaurants/{id}", resId)
                .retrieve()
                .bodyToMono(RestaurantDto.class)
                .block();

        return restaurantDto;
    }

    //get all restaurant
    public List<RestaurantDto> getAll(){
//        return webClient.get().uri("/api/v1/restaurants")
//                .retrieve()
//                .bodyToFlux(RestaurantDto.class)
//                .collectList()
//                .block();

        return webClient.baseUrl(AppConstants.RESTAURANT_SERVICE_URL).build().get().uri("/api/v1/restaurants")
                .retrieve()
                .bodyToFlux(RestaurantDto.class)
                .collectList()
                .block();
    }

    //post request

    public RestaurantDto createRestaurant(RestaurantDto restaurantDto){
//        return
//                webClient.post()
//                        .uri("/api/v1/restaurants")
//                        .bodyValue(restaurantDto)
//                        .retrieve()
//                        .bodyToMono(RestaurantDto.class)
//                        .block();

        return
                webClient.baseUrl(AppConstants.RESTAURANT_SERVICE_URL).build().post()
                        .uri("/api/v1/restaurants")
                        .bodyValue(restaurantDto)
                        .header("Authorization", "Bearer sdag")
                        .retrieve()
                        .bodyToMono(RestaurantDto.class)
                        .block();
    }

    public RestaurantDto updateRestaurant(
            String restaurantId,
            RestaurantDto restaurantDto){

        return webClient.baseUrl(AppConstants.RESTAURANT_SERVICE_URL).build().put()
                .uri("/api/v1/restaurants/{id}", restaurantId)
                .bodyValue(restaurantDto)
                .retrieve()
                .bodyToMono(RestaurantDto.class)
                .block();
    }

    //non blocking -->

    //get by id

    public Mono<RestaurantDto> getResById(String resId){
        return  webClient.baseUrl(AppConstants.RESTAURANT_SERVICE_URL).build().get().uri("/api/v1/restaurants/{id}", resId)
                .retrieve()
                .bodyToMono(RestaurantDto.class);
    }

    public Flux<RestaurantDto> getAllNon(){
        return  webClient.baseUrl(AppConstants.RESTAURANT_SERVICE_URL).build().get() .uri("/api/v1/restaurants")
                .retrieve()
                .bodyToFlux(RestaurantDto.class);
    }
}
