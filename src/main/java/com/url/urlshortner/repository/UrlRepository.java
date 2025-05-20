package com.url.urlshortner.repository;

import com.url.urlshortner.entities.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<Url,String> {

}
