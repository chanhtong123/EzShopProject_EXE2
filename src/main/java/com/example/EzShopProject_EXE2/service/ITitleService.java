package com.example.EzShopProject_EXE2.service;

import com.example.EzShopProject_EXE2.dto.TitleDto;
import com.example.EzShopProject_EXE2.exception.DataNotFoundException;

import java.util.List;

public interface ITitleService {
     TitleDto createTitle(TitleDto titleDto);
     TitleDto updateTitle(Long id, TitleDto titleDto) throws DataNotFoundException;
     void deleteTitle(Long id) throws DataNotFoundException;
     List<TitleDto> getAllTitles();
     TitleDto getTitleById(Long id) throws DataNotFoundException;

}
