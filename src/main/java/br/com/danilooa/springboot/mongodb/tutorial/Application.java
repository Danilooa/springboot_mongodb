package br.com.danilooa.springboot.mongodb.tutorial;

import br.com.danilooa.springboot.mongodb.tutorial.model.Product;
import br.com.danilooa.springboot.mongodb.tutorial.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.math.BigDecimal;

@SpringBootApplication
@EnableMongoRepositories
public class Application implements CommandLineRunner {

    @Autowired
    ProductRepository productRepository;

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    public void run(String... args) {
        Product product = new Product();
        product.setName("Gipson Les Paul");
        product.setDescription(" G doesn't tune");
        product.setPrice(new BigDecimal("2000.849"));
        Product save = productRepository.save(product);

        System.out.println("Product was saved: " + save);

        System.out.println(productRepository.findAll());
    }
}
