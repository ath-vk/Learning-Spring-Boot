package com.example.jpaTutorial.jpaTuts.controller;

import com.example.jpaTutorial.jpaTuts.entities.ProductEntity;
import com.example.jpaTutorial.jpaTuts.repositories.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final int PAGE_SIZE = 3;
    private final ProductRepository productRepository;


    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    List<ProductEntity> getAllProductsSortedByPrice() {
        List<ProductEntity> productEntities = productRepository.findByOrderByPrice();
        return productEntities;
    }

    @GetMapping(path = "/sorted")
    List<ProductEntity> getAllProductsSortedByField(@RequestParam(defaultValue = "id") String sortBy) {
        //return productRepository.findBy(Sort.by(Sort.Direction.DESC,  sortBy, "price"));
        return productRepository.findBy(Sort.by(Sort.Order.desc(sortBy),
                Sort.Order.asc("title")));
    }

    @GetMapping(path = "/paged")
    List<ProductEntity> getAllProductsPaged(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "0") Integer pageNumber
    ) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                PAGE_SIZE,
                Sort.by(Sort.Order.desc(sortBy)));
        return productRepository.findAll(pageable).getContent();
    }

    @GetMapping(path = "/filterAndPage")
    List<ProductEntity> getFilteredResults(
            @RequestParam(defaultValue = "") String contained,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "0") Integer pageNumber
    ) {
        return productRepository.findByTitleContainingIgnoreCase(
                contained,
                PageRequest.of(
                        pageNumber,
                        PAGE_SIZE,
                        Sort.by(sortBy)
                )
        );
    }
}
