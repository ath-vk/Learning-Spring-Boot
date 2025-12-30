package com.example.jpaTutorial.jpaTuts.repositories;

import com.example.jpaTutorial.jpaTuts.entities.ProductEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findAByTitle(String pepsi);

    List<ProductEntity> findByOrderByPrice();

    List<ProductEntity> findByCreatedAtAfter(LocalDateTime of);

    @Query("select e.title from ProductEntity e where e.title=:title and e.price=:price")
    Optional<ProductEntity> findByTitleAndPrice(String title);

    List<ProductEntity> findBy(Sort by);

    List<ProductEntity> findByTitleContainingIgnoreCase(String contained, PageRequest of);
}
