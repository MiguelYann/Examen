package com.rizomm.m2.exam.business.services.impl;

import com.rizomm.m2.exam.business.converters.ProductConverter;
import com.rizomm.m2.exam.business.dto.ProductDto;
import com.rizomm.m2.exam.business.entities.Product;
import com.rizomm.m2.exam.business.repositories.ProductRepository;
import com.rizomm.m2.exam.business.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductConverter productConverter;


    @Override
    public List<ProductDto> getAllProduct() {

        List<Product> productList = productRepository.findAll();
        List<ProductDto> productDtos =productList.stream()
                .map((this::apply)).collect(Collectors.toList());

        return productDtos;
    }

    @Override
    public Optional<ProductDto> getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            return Optional.of(productConverter.convert(optionalProduct.get()));
        }
        return null;
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {

        productRepository.save(productConverter.convert(productDto));

        return productDto;
    }

    @Override
    public void deleteProduct(ProductDto productDto) {

            productRepository.deleteById(productConverter.convert(productDto).getId());

        }



    private ProductDto apply(Product product1) {
        return productConverter.convert(product1);
    }
}
