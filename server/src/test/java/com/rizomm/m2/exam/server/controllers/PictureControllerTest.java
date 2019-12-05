package com.rizomm.m2.exam.server.controllers;

import com.rizomm.m2.exam.business.converters.PictureConverter;
import com.rizomm.m2.exam.business.converters.ProductConverter;
import com.rizomm.m2.exam.business.entities.*;
import com.rizomm.m2.exam.business.services.PictureService;
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
import java.util.HashMap;
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
public class PictureControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    PictureConverter pictureConverter;

    @MockBean
    PictureService pictureService;
    public void init() {

        Picture picture = Picture.builder().id(1L).sources(new HashMap<>()).id(1L).labels(Arrays.asList("label1","lanel2")).build();


        when(pictureService.getPictureById(1L)).thenReturn(Optional.of(pictureConverter.convert(picture)));

    }

    @Test
    public void whenPictureExistThenReturnIy() throws  Exception{

        mockMvc
                .perform(get("/pictures").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }






}
