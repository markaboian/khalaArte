package com.khala_arte.ms_products.service.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khala_arte.ms_products.domain.Product;
import com.khala_arte.ms_products.dto.ProductDTO;
import com.khala_arte.ms_products.repository.IProductRepository;
import com.khala_arte.ms_products.service.interfaces.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final ObjectMapper mapper;

    private ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = mapper.convertValue(productDTO, Product.class);
        productRepository.save(product);
        return mapper.convertValue(product, ProductDTO.class);
    }

    @Override
    public Optional<ProductDTO> getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        ProductDTO productDTO;

        if (product.isPresent()) {
            productDTO = mapper.convertValue(product, ProductDTO.class);
            return Optional.ofNullable(productDTO);
        }
        else {
            return Optional.empty();
        }

    }

    @Override
    public Optional<ProductDTO> getProductByName(String name) {
        Optional<Product> product = productRepository.getProductByName(name);
        ProductDTO productDTO;

        if (product.isPresent()) {
            productDTO = mapper.convertValue(product, ProductDTO.class);
            return Optional.ofNullable(productDTO);
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public Set<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> mapper.convertValue(product, ProductDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        return saveProduct(productDTO);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        return saveProduct(productDTO);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
