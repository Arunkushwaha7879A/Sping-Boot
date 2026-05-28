package com.substring.foodie.substring_foodie.controller;

import com.substring.foodie.substring_foodie.dto.RestaurantDto;
import com.substring.foodie.substring_foodie.exception.InvalidFilePathException;
import com.substring.foodie.substring_foodie.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    @Value("${restaurant.file.path}")
    private String banFolderPath;

    private RestaurantService restaurantService;


    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    private Logger logger = LoggerFactory.getLogger(RestaurantController.class);


    //add
    @PostMapping
    public ResponseEntity<RestaurantDto> add(@RequestBody RestaurantDto restaurantDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.add(restaurantDto));
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDto> update(@RequestBody RestaurantDto restaurantDto , @PathVariable String restaurantId){
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.update(restaurantDto, restaurantId));
    }

    @GetMapping
    public ResponseEntity<Page<RestaurantDto>> findAll(
            @RequestParam(value = "page" , required = false , defaultValue = "0") int page,
            @RequestParam(value = "size" , required = false , defaultValue = "10") int size,
            @RequestParam(value = "sortBy" , required = false , defaultValue = "name") String sortBy,
            @RequestParam(value = "sortDir" , required = false , defaultValue = "desc") String  sortDir
    ){

        Sort sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);


        return ResponseEntity.ok(restaurantService.getAll(pageable));
    }

    @PostMapping("/upload-banner/{restaurantId}")
    public ResponseEntity<?> uploadFile(
            @RequestParam("banner")MultipartFile banner,
            @PathVariable String restaurantId) throws IOException {


        RestaurantDto restaurantDto = restaurantService.uploadBanner(banner, restaurantId);

        return  ResponseEntity.ok(restaurantDto);
    }

    //api to serve banner
    @GetMapping("{restaurantId}/banner")
    public ResponseEntity<Resource> serveFile(@PathVariable String restaurantId) throws  IOException
    {
        RestaurantDto restaurantDto = restaurantService.get(restaurantId);

        String fullPath = banFolderPath + restaurantDto.getBanner();

        Path filePath= Paths.get(fullPath);

        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists()) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG)
//                    .header(HttpHeaders.CONTENT_DISPOSITION , "attachment; filename=\"" + restaurantDto.getBanner()+ "\"" )
                    .body(resource);
        }
        else{
            throw new InvalidFilePathException("File not found :" + fullPath);
        }
    }



}
