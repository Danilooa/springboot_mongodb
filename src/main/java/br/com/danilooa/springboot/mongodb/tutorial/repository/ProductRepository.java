package br.com.danilooa.springboot.mongodb.tutorial.repository;

import br.com.danilooa.springboot.mongodb.tutorial.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    @Query("{}")
    Flux<Product> findAllPaged(Pageable pageable);

    @Aggregation(pipeline = {
            "{$match: {name: { $regex: /?0/}}}",
            "{$group: {_id: 0, count: {$sum: 1}}}",
            "{$project: { _id: 0, brandName: {$literal:'?0'}, count:1 }}"
    })
    Flux<BrandCount> brandCount(String brandName);

}
