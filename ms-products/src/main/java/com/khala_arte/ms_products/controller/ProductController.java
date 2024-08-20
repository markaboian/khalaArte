package com.khala_arte.ms_products.controller;

import com.khala_arte.ms_products.domain.Product;
import com.khala_arte.ms_products.dto.ProductDTO;
import com.khala_arte.ms_products.service.implementations.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        ResponseEntity response = null;
        Optional<ProductDTO> productDTO = productService.getProductById(id);
        if (productDTO.isPresent()) {
            response = new ResponseEntity<>(productDTO, HttpStatus.OK);
        }
        else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with id of: " + id + " not found");
        }
        return response;
    }

    @GetMapping("/getProductByName/{name}")
    public ResponseEntity<?> getProductByName(@PathVariable String name) {
        ResponseEntity response = null;
        Optional<ProductDTO> productDTO = productService.getProductByName(name);
        if (productDTO.isPresent()) {
            response = new ResponseEntity<>(productDTO, HttpStatus.OK);
        }
        else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with name of: " + name + " not found");
        }
        return response;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllProducts() {
        ResponseEntity response = null;
        Set<ProductDTO> productDTOS = productService.getAllProducts();
        if (productDTOS.isEmpty()) {
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("No users found");
        }
        else {
            response = new ResponseEntity<>(productDTOS, HttpStatus.OK);
        }
        return response;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO) {
        try {
            ProductDTO newProductDTO = productService.addProduct(productDTO);
            return new ResponseEntity<>("Product created successfully - " + newProductDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while creating the product - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO) {
        try {
            ProductDTO updatedProductDTO = productService.updateProduct(productDTO);
            return new ResponseEntity<>("Product updated successfully - " + updatedProductDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while updating the product - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProductById(id);
            return new ResponseEntity<>("Product with id of: " + id + " deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while deleting the product with id of: " + id + " - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
