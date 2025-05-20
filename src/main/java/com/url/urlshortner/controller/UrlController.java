package com.url.urlshortner.controller;


import com.url.urlshortner.dto.ShortenUrlRequest;
import com.url.urlshortner.dto.ShortenUrlResponse;
import com.url.urlshortner.entities.Url;
import com.url.urlshortner.repository.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.Instant;

@RestController
public class UrlController {

    @Autowired
    UrlRepository repository;

    @PostMapping(value ="/shorten-url")
    public ResponseEntity<ShortenUrlResponse>shortnerUrl(@RequestBody ShortenUrlRequest request, HttpServletRequest servletRequest){

       String id= RandomStringUtils.randomAlphanumeric(5,10);
       do{
            id=RandomStringUtils.randomAlphanumeric(5,10);
       }while (repository.existsById(id));
            repository.save(new Url(id, request.url(), Instant.now().plusSeconds(60)));
            var redirecturl = servletRequest.getRequestURL().toString().replace("shorten-url",id);
        return ResponseEntity.ok(new ShortenUrlResponse(redirecturl));

    }
    @GetMapping("{id}")
    public ResponseEntity<Void>redirect(@PathVariable("id") String id){
        var url = repository.findById(id);
    if(url.isEmpty()){
        return ResponseEntity.notFound().build();
    }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.get().getFullUrl()));
        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }


}
