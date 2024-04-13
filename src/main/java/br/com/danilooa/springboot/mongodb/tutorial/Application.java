package br.com.danilooa.springboot.mongodb.tutorial;

import br.com.danilooa.springboot.mongodb.tutorial.model.Product;
import br.com.danilooa.springboot.mongodb.tutorial.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class Application implements CommandLineRunner {

    @Autowired
    ProductRepository productRepository;

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    public void run(String... args) {
        Product product = new Product();
        product.setName("Gibson Les Paul");
        product.setDescription("G doesn't tune");
        product.setPrice(new BigDecimal("2000.849"));

        Mono<Product> insert = productRepository.insert(product);
        Flux<Product> findAll = productRepository.findAll();

        Flux.merge(insert).thenMany(findAll).doOnNext(System.out::println).blockLast();
    }
}
