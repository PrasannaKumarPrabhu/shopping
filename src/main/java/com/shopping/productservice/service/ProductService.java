package com.shopping.productservice.service;

import com.shopping.productservice.dto.ProductRequest;
import com.shopping.productservice.dto.ProductResponse;
import com.shopping.productservice.model.Product;
import com.shopping.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ResponseEntity<String> createProduct(ProductRequest productRequest){
        try {
            Product product = Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name(productRequest.getName())
                    .description(productRequest.getDescription())
                    .price(productRequest.getPrice())
                    .build();
            productRepository.save(product);
            log.info("Product {} is saved", product.getId());

            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Exception", HttpStatus.BAD_REQUEST);

    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
