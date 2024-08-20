package com.khala_arte.ms_products.service.interfaces;

import com.khala_arte.ms_products.dto.ProductDTO;

import java.util.Optional;
import java.util.Set;

public interface IProductService {

    Optional<ProductDTO> getProductById(Long id);
    Optional<ProductDTO> getProductByName(String name);
    Set<ProductDTO> getAllProducts();

    ProductDTO addProduct(ProductDTO productDTO);
    ProductDTO updateProduct(ProductDTO productDTO);

    void deleteProductById(Long id);
}
