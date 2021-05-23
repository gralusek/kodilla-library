package com.library.controller;

import com.library.Dto.TitleDto;
import com.library.domain.Title;
import com.library.mapper.TitleMapper;
import com.library.service.TitleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/title")
public class TitleController {
    private final TitleMapper titleMapper;
    private final TitleService titleService;

    public TitleController(TitleMapper titleMapper, TitleService titleService) {
        this.titleMapper = titleMapper;
        this.titleService = titleService;
    }

    @PostMapping
    public Title addTitle(@RequestBody TitleDto titleDto) {
        Title title = titleMapper.mapToTitle(titleDto);
        return titleService.addTitle(title);
    }

    @GetMapping
    public int countBookCopies(@RequestParam Long bookId) {
        return titleService.countBookCopies(bookId);
    }
}
