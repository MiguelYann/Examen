package com.rizomm.m2.exam.business.services.impl;

import com.rizomm.m2.exam.business.converters.PictureConverter;
import com.rizomm.m2.exam.business.dto.PictureDto;
import com.rizomm.m2.exam.business.dto.ProductDto;
import com.rizomm.m2.exam.business.entities.Picture;
import com.rizomm.m2.exam.business.repositories.PictureRepository;
import com.rizomm.m2.exam.business.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    PictureRepository pictureRepository;
    @Autowired
    PictureConverter pictureConverter;


    @Override
    public List<PictureDto> getAllPictures() {

        List<Picture> pictureList = pictureRepository.findAll();
        List<PictureDto> pictureDtos = pictureList.stream()
                .map((this::apply)).collect(Collectors.toList());

        return pictureDtos;
    }

    @Override
    public Optional<PictureDto> getPictureById(Long id) {
        Optional<Picture> optionalPicture = pictureRepository.findById(id);
        if (optionalPicture.isPresent()) {
            return Optional.of(pictureConverter.convert(optionalPicture.get()));
        }
        return null;
    }

    @Override
    public PictureDto addPicture(PictureDto pictureDto) {

        pictureRepository.save(pictureConverter.convert(pictureDto));

        return pictureDto;
    }

    @Override
    public void deletePicture(PictureDto pictureDto) {

        pictureRepository.deleteById(pictureConverter.convert(pictureDto).getId());

    }

    private PictureDto apply(Picture picture) {
        return pictureConverter.convert(picture);
    }
}
