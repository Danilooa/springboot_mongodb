package br.com.danilooa.springboot.mongodb.tutorial;

import br.com.danilooa.springboot.mongodb.tutorial.model.Product;
import br.com.danilooa.springboot.mongodb.tutorial.repository.BrandCount;
import br.com.danilooa.springboot.mongodb.tutorial.repository.ProductRepository;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

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
        Flux<Product> pagedFind = productRepository.findAllPaged(PageRequest.of(2, 5));
        Mono<Long> count = productRepository.count();
        Flux<BrandCount> brandCound = productRepository.brandCount("Gibson");
        Mono<Product> findById = productRepository.findById("661a5412f36ff6f3f5535ec3");

        Flux
                .merge(insert)
                .doOnNext((insertedProduct) -> System.out.println("Inserted product:" + insertedProduct))
                .flatMap(printAndSave())
                .doOnNext((updatedProduct) -> System.out.println("Updated product:" + updatedProduct))
                .flatMap(printAndDelete())
                .doOnNext((deleteResult) -> System.out.println("Was it deleted? " + deleteResult))
                .thenMany(findAll)
                .doOnNext(System.out::println)
                .then(count)
                .doOnNext(System.out::println)
                .thenMany(brandCound)
                .doOnNext(System.out::println)
                .thenMany(pagedFind)
                .doOnNext(System.out::println)
                .then(findById)
                .doOnNext(System.out::println)
                .block();
    }

    private Function<Product, Publisher<? extends Product>> printAndSave() {
        return (p) -> {
            p.setName(p.getName() + " " + System.currentTimeMillis());
            return productRepository.save(p);
        };
    }

    private Function<Product, Publisher<? extends Boolean>> printAndDelete() {
        return (p) -> {
            try {
                productRepository.delete(new Product()).block();
            } catch (Exception e) {
                return Mono.just(Boolean.FALSE);
            }
            return Mono.just(Boolean.TRUE);
        };
    }

}
