package com.library.controller;

import com.library.Dto.ReaderDto;
import com.library.domain.Reader;
import com.library.mapper.ReaderMapper;
import com.library.service.ReaderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/reader")
public class ReaderController {
    private final ReaderMapper readerMapper;
    private final ReaderService readerService;

    public ReaderController(ReaderMapper readerMapper, ReaderService readerService) {
        this.readerMapper = readerMapper;
        this.readerService = readerService;
    }

    @PostMapping
    public Reader addReader(@RequestBody ReaderDto readerDto) {
        Reader reader = readerMapper.mapToReader(readerDto);
        return readerService.addReader(reader);
    }
}
