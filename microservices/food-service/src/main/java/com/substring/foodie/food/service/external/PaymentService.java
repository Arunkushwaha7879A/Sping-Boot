package com.substring.foodie.food.service.external;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "payment-service" , url = "http://localhost:9091")
public interface PaymentService {

}
