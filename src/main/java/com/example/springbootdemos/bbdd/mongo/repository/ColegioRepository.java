package com.example.springbootdemos.bbdd.mongo.repository;

import com.example.springbootdemos.bbdd.mongo.model.Colegio;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ColegioRepository extends MongoRepository<Colegio, String> {

}
