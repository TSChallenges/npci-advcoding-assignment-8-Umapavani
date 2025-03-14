package com.mystore.app.repositories;

import com.mystore.app.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    // TODO
    Page<Product> findAll(Pageable pageable);
    List<Product> findByNameIgnoreCase(String name);

    List<Product> findByCategory(String category);

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    List<Product> findByStockQuantityBetween(Integer minStock, Integer maxStock);
}
