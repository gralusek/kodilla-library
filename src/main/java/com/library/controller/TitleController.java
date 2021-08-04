package com.library.controller;

import com.library.Dto.ReaderDto;
import com.library.Dto.TitleDto;
import com.library.domain.Reader;
import com.library.domain.Title;
import com.library.mapper.TitleMapper;
import com.library.service.TitleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/title")
public class TitleController {
    private final TitleMapper titleMapper;
    private final TitleService titleService;

    public TitleController(TitleMapper titleMapper, TitleService titleService) {
        this.titleMapper = titleMapper;
        this.titleService = titleService;
    }

    @PostMapping(value = "addTitle")
    public Title addTitle(@RequestBody TitleDto titleDto) {
        Title title = titleMapper.mapToTitle(titleDto);
        return titleService.addTitle(title);
    }

    @GetMapping(value = "countBookCopies")
    public int countBookCopies(@RequestParam Long bookId) {
        return titleService.countBookCopies(bookId);
    }

    @DeleteMapping(value = "deleteTitleById")
    public void deleteTitleById(@RequestParam Long id) {
        titleService.deleteById(id);
    }

    @DeleteMapping(value = "deleteTitleByName")
    public void deleteTitleByName(@RequestParam String name) {
        titleService.deleteByName(name);
    }

    @GetMapping(value = "getTitle")
    public TitleDto getTitle(@RequestParam Long id) {
        return titleMapper.mapToTileDto(
                titleService.findById(id).orElseThrow(() -> new RuntimeException("Title with given ID was not found")));
    }

    @GetMapping(value = "getTitles")
    public List<TitleDto> getTitles() {
        List<Title> titles = titleService.findAll();
        return titleMapper.mapToTitleDtoList(titles);
    }
}
