package com.rizomm.m2.exam.server.controllers;

import com.rizomm.m2.exam.business.converters.PictureConverter;
import com.rizomm.m2.exam.business.converters.ProductConverter;
import com.rizomm.m2.exam.business.dto.PictureDto;
import com.rizomm.m2.exam.business.dto.ProductDto;
import com.rizomm.m2.exam.business.entities.Picture;
import com.rizomm.m2.exam.business.services.PictureService;
import com.rizomm.m2.exam.business.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pictures")
public class PictureController {

    @Autowired
    PictureService pictureService;

    private PictureConverter pictureConverter;

    public PictureController(
            PictureService pictureService,
            PictureConverter pictureConverter) {
        this.pictureService = pictureService;
        this.pictureConverter = pictureConverter;
    }


    @GetMapping()
    public List<PictureDto> getAllPicture(){
        return pictureService.getAllPictures();
    }

    @PostMapping("")
    public PictureDto addPicture(@RequestBody PictureDto pictureDto){

        pictureService.addPicture(pictureDto);
        return pictureDto;
    }

    @GetMapping("/{id}")
    public PictureDto getPictureById(@PathVariable Long id){
        Optional<PictureDto> pictureDto =pictureService.getPictureById(id);
        return pictureDto.get();
    }

    @DeleteMapping("/{id}")
    public void deletePictureDto(@RequestBody PictureDto pictureDto){
        pictureService.deletePicture(pictureDto);
    }

    @PutMapping("/{id}")
    public PictureDto updatePicture(@RequestBody PictureDto pictureDto, @PathVariable int id){
        return pictureDto;
    }

}
