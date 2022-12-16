package com.example.demo;

import com.example.demo.entity.Product;
import com.example.demo.entity.Supplier;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.SupplierRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(SupplierRepository repository ) {
		return args -> {
////,ProductRepository pr
			repository.save(new Supplier("MOFIANG","CHINA"));
			repository.save(new Supplier("GAN","USA"));
			repository.save(new Supplier("QIYI","CHINA"));
//
////			pr.save(new Product("MF3RS v2",320000,1));
////			pr.save(new Product("GAN DOU",320000,2));
////			pr.save(new Product("MF3RS v1",320000,1));
////			pr.save(new Product("MF3RS v2",320000,3));
		};
	}


}
