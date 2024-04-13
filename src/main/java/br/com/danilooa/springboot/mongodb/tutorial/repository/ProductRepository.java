package br.com.danilooa.springboot.mongodb.tutorial.repository;

import br.com.danilooa.springboot.mongodb.tutorial.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}
