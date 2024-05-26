package com.example.EzShopProject_EXE2.service.impl;

import com.example.EzShopProject_EXE2.dto.TitleDto;
import com.example.EzShopProject_EXE2.exception.DataNotFoundException;
import com.example.EzShopProject_EXE2.model.Title;
import com.example.EzShopProject_EXE2.repository.TitleRepository;
import com.example.EzShopProject_EXE2.service.ITitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TitleService implements ITitleService {
    private final TitleRepository titleRepository;

    @Autowired
    public TitleService(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    public TitleDto createTitle(TitleDto titleDto) {
        Title title = mapToEntity(titleDto);
        Title savedTitle = titleRepository.save(title);
        return mapToDto(savedTitle);
    }

    public TitleDto updateTitle(Long id, TitleDto titleDto) throws DataNotFoundException {
        Title existingTitle = titleRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find title with id: " + id));

        existingTitle.setName(titleDto.getName());
        Title updatedTitle = titleRepository.save(existingTitle);
        return mapToDto(updatedTitle);
    }

    public void deleteTitle(Long id) throws DataNotFoundException {
        Title existingTitle = titleRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find title with id: " + id));

        titleRepository.delete(existingTitle);
    }

    public List<TitleDto> getAllTitles() {
        List<Title> titles = titleRepository.findAll();
        return titles.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public TitleDto getTitleById(Long id) throws DataNotFoundException {
        Title title = titleRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find title with id: " + id));
        return mapToDto(title);
    }

    private TitleDto mapToDto(Title title) {
        TitleDto titleDto = new TitleDto();
        titleDto.setId(title.getId());
        titleDto.setName(title.getName());
        return titleDto;
    }

    private Title mapToEntity(TitleDto titleDto) {
        Title title = new Title();
        title.setId(titleDto.getId());
        title.setName(titleDto.getName());
        return title;
    }
}
