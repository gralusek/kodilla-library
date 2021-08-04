package com.library.controller;

import com.library.Dto.BookDto;
import com.library.Dto.ReaderDto;
import com.library.domain.Book;
import com.library.domain.Reader;
import com.library.mapper.ReaderMapper;
import com.library.service.ReaderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/reader")
public class ReaderController {
    private final ReaderMapper readerMapper;
    private final ReaderService readerService;

    public ReaderController(ReaderMapper readerMapper, ReaderService readerService) {
        this.readerMapper = readerMapper;
        this.readerService = readerService;
    }

    @PostMapping(value = "addReader")
    public Reader addReader(@RequestBody ReaderDto readerDto) {
        Reader reader = readerMapper.mapToReader(readerDto);
        return readerService.addReader(reader);
    }

    @DeleteMapping(value = "deleteReader")
    public void deleteReader(@RequestParam Long id) {
        readerService.deleteById(id);
    }

    @GetMapping(value = "getReader")
    public ReaderDto getReader(@RequestParam Long id) {
        return readerMapper.mapToReaderDto(
                readerService.findById(id).orElseThrow(() -> new RuntimeException("Reader with given ID was not found")));
    }

    @GetMapping(value = "getReaders")
    public List<ReaderDto> getReaders() {
        List<Reader> readers = readerService.findAll();
        return readerMapper.mapToReaderDtoList(readers);
    }
}
