package com.mystore.app.rest;

import com.mystore.app.entity.Product;
import com.mystore.app.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping()
    public ResponseEntity<Object> addProduct(@RequestBody @Valid Product product) {
        Product p = productService.addProduct(product);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @GetMapping
    public Page<Product> getAllProducts(@RequestParam("page")  int page, @RequestParam("size") int size,@RequestParam(value = "sortBy")  String sortBy, @RequestParam(value = "sortDir") String sortDir) {
        return productService.getAllProducts(page,size,sortBy,sortDir);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id) {
        Product p = productService.getProduct(id);
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @Valid @RequestBody Product product) {
        Product p = productService.updateProduct(id, product);
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id) {
        String message = productService.deleteProduct(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // TODO: API to search products by name

    @GetMapping("/search")
    public ResponseEntity<List<Product>> serachProductByName(@RequestParam("name") String name) {
        List<Product> productList = productService.searchProductByName(name);
        if(productList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(productList);
    }
    // TODO: API to filter products by category

    @GetMapping("/filter/category")
    public ResponseEntity<List<Product>> getProductListByCategoryName(@RequestParam("category") String category) {
        List<Product> productList = productService.getProductListByCategoryName(category);
        if(productList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(productList);
    }
    // TODO: API to filter products by price range
    @GetMapping("/filter/price")
    public ResponseEntity<List<Product>> getProductListWithInPriceRange(@Valid @RequestParam("minPrice") Double minPrice,@Valid @RequestParam("maxPrice") Double maxPrice) {
        List<Product> productList = productService.getProductListWithInPriceRange(minPrice,maxPrice);
        if(productList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(productList);
    }

    // TODO: API to filter products by stock quantity range

    @GetMapping("/filter/stock")
    public ResponseEntity<List<Product>> getProductListByStock(@Valid@RequestParam("minStock") Integer minStock,@Valid@RequestParam("maxStock") Integer maxStock) {
        List<Product> productList = productService.getProductListByStock(minStock,maxStock);
        if(productList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(productList);
    }
}
