package com.mystore.app.service;

import com.mystore.app.entity.Product;
import com.mystore.app.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private Integer currentId = 1;

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        product.setId(currentId++);
        productRepository.save(product);
        return product;
    }

    public Page<Product> getAllProducts(int page, int size,String sortBy, String sortDir) {
       sortBy = (sortBy == null || sortBy.isBlank() ) ? "id" : sortBy;
       sortDir = (sortDir == null || sortDir.isBlank()) ? "desc" : sortDir;

        Sort sort = sortDir.equalsIgnoreCase("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page,size,sort);
        return productRepository.findAll(pageable);
    }

    public Product getProduct(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElse(null);
    }

    public Product updateProduct(Integer id, Product product) {
        Optional<Product> p = productRepository.findById(id);
        if (p.isEmpty()) return null;
        Product productToUpdate = p.get();
        productToUpdate.setName(product.getName());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setCategory(product.getCategory());
        productToUpdate.setStockQuantity(product.getStockQuantity());
        productRepository.save(productToUpdate);
        return productToUpdate;
    }

    public String deleteProduct(Integer id) {
        Optional<Product> p = productRepository.findById(id);
        if (p.isEmpty()) return "Product Not Found";
        productRepository.delete(p.get());
        return "Product Deleted Successfully";
    }

    // TODO: Method to search products by name

    public List<Product> searchProductByName(String name) {
        return productRepository.findByNameIgnoreCase(name);

    }

    public List<Product> getProductListByCategoryName(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getProductListWithInPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice,maxPrice);
    }

    public List<Product> getProductListByStock(Integer minStock, Integer maxStock) {
        return productRepository.findByStockQuantityBetween(minStock,maxStock);
    }




}
