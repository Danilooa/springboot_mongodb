package br.com.danilooa.springboot.mongodb.tutorial;

import br.com.danilooa.springboot.mongodb.tutorial.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class Application implements CommandLineRunner {

    @Autowired
    ProductRepository productRepository;

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    public void run(String... args) {
        System.out.println(productRepository.findAll());
    }
}
