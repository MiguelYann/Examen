package com.rizomm.m2.exam.business.services;

import com.rizomm.m2.exam.business.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDto> getAllProduct();

    Optional<ProductDto> getProductById(Long id);

    ProductDto addProduct(ProductDto productDto);

   void deleteProduct (ProductDto productDto);


}
