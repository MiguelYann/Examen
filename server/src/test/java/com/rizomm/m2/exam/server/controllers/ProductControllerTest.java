package com.rizomm.m2.exam.server.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rizomm.m2.exam.business.converters.ProductConverter;
import com.rizomm.m2.exam.business.entities.Product;
import com.rizomm.m2.exam.business.entities.State;
import com.rizomm.m2.exam.business.entities.Transition;
import com.rizomm.m2.exam.business.entities.Tree;
import com.rizomm.m2.exam.business.services.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ProductConverter productConverter;

    @MockBean
    ProductService productService;
    public void init() {

        Product product = Product.builder().id(1L).superModel("mon modele").build();


        when(productService.getProductById(1L)).thenReturn(Optional.of(productConverter.convert(product)));

    }

    @Test
    public void whenProductExistThenReturnIt() throws  Exception{

        mockMvc
                .perform(get("/products").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    public void whenProductWhenPostThenReturnIt() throws  Exception{

        mockMvc
                .perform(post("/products").accept(MediaType.APPLICATION_JSON)
                .content(convertJsonToString(Product.builder().id(1L).superModel("model"))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }
    public static String convertJsonToString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}
