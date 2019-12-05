package com.rizomm.m2.exam.server.controllers;

import com.rizomm.m2.exam.business.converters.ProductConverter;
import com.rizomm.m2.exam.business.dto.ProductDto;
import com.rizomm.m2.exam.business.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  ProductService productService;

  private ProductConverter productConverter;

  public ProductController(
      ProductService productService,
      ProductConverter productConverter) {
    this.productService = productService;
    this.productConverter = productConverter;
  }


  @GetMapping()
  public List<ProductDto> getAllProduct(){
    return productService.getAllProduct();
  }

  @PostMapping()
  public ProductDto addProduct(@RequestBody ProductDto productDto){

    productService.addProduct(productDto);
    return productDto;
  }

  @GetMapping("/{id}")
  public ProductDto getProductDtoById(@PathVariable Long id){
    Optional<ProductDto> productDto =productService.getProductById(id);
    return productDto.get();
  }

  @DeleteMapping("/{id}")
  public void deleteProductDto(@RequestBody ProductDto productDto){
     productService.deleteProduct(productDto);
  }

  @PutMapping("/{id}")
  public ProductDto updateproductDto(@RequestBody ProductDto productDto, @PathVariable int id){
    return productDto;
  }

}
