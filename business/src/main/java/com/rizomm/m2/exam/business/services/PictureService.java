package com.rizomm.m2.exam.business.services;

import com.rizomm.m2.exam.business.dto.PictureDto;

import java.util.List;
import java.util.Optional;

public interface PictureService {

    List<PictureDto> getAllPictures();

    Optional<PictureDto> getPictureById(Long id);

    PictureDto addPicture(PictureDto pictureDto);

    void deletePicture(PictureDto pictureDto);
}
