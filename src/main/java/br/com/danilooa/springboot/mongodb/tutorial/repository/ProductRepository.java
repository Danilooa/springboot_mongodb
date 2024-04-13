package br.com.danilooa.springboot.mongodb.tutorial.repository;

import br.com.danilooa.springboot.mongodb.tutorial.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

}
