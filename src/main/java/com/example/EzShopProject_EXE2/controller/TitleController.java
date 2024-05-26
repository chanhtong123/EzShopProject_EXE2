package com.example.EzShopProject_EXE2.controller;

import com.example.EzShopProject_EXE2.dto.TitleDto;
import com.example.EzShopProject_EXE2.exception.DataNotFoundException;
import com.example.EzShopProject_EXE2.service.ITitleService;
import com.example.EzShopProject_EXE2.service.impl.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/titles")
public class TitleController {
    private final ITitleService ititleService;

    @Autowired
    public TitleController(TitleService titleService) {
        this.ititleService = titleService;
    }

    @PostMapping
    public ResponseEntity<TitleDto> createTitle(@RequestBody TitleDto titleDto) {
        TitleDto createdTitle = ititleService.createTitle(titleDto);
        return ResponseEntity.ok(createdTitle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TitleDto> updateTitle(@PathVariable Long id, @RequestBody TitleDto titleDto) {
        try {
            TitleDto updatedTitle = ititleService.updateTitle(id, titleDto);
            return ResponseEntity.ok(updatedTitle);
        } catch (DataNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTitle(@PathVariable Long id) {
        try {
            ititleService.deleteTitle(id);
            return ResponseEntity.noContent().build();
        } catch (DataNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TitleDto>> getAllTitles() {
        List<TitleDto> titles = ititleService.getAllTitles();
        return ResponseEntity.ok(titles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TitleDto> getTitleById(@PathVariable Long id) {
        try {
            TitleDto title = ititleService.getTitleById(id);
            return ResponseEntity.ok(title);
        } catch (DataNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
