package com.example.jpaTutorial.jpaTuts;

import com.example.jpaTutorial.jpaTuts.entities.ProductEntity;
import com.example.jpaTutorial.jpaTuts.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class JpaTutsApplicationTests {

    @Autowired
    private ProductRepository productRepository;

	@Test
	void testRepository() {
        ProductEntity productEntity = ProductEntity.builder()
                .sku("parle1234")
                .title("parle biscuit")
                .price(BigDecimal.valueOf(11111))
                .quantity(12)
                .build();

        ProductEntity savedProductEntity = productRepository.save(productEntity);
        System.out.println(savedProductEntity);
	}

    @Test
    void getRepository() {
        ProductEntity productEntity = ProductEntity.builder()
                .sku("parle1234")
                .title("parle biscuit")
                .price(BigDecimal.valueOf(11111))
                .quantity(12)
                .build();

        ProductEntity savedProductEntity = productRepository.save(productEntity);

        List<ProductEntity> entities = productRepository.findByCreatedAtAfter(
                LocalDateTime.of(2024, 1, 1, 0, 0, 0)
        );
        System.out.println(entities);
    }

}
