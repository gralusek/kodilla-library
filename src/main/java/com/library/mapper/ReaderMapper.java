package com.library.mapper;

import com.library.Dto.BorrowDto;
import com.library.Dto.ReaderDto;
import com.library.domain.Borrow;
import com.library.domain.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReaderMapper {

    @Autowired
    BorrowMapper borrowMapper;

    public Reader mapToReader(final ReaderDto readerDto) {
        return new Reader(
                readerDto.getId(),
                readerDto.getName(),
                readerDto.getSurname(),
                readerDto.getCreationDate(),
                borrowMapper.mapToBorrowList(readerDto.getBorrowsDtoList())
        );
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        return new ReaderDto(
                reader.getId(),
                reader.getName(),
                reader.getSurname(),
                reader.getCreationDate(),
                borrowMapper.mapToBorrowDtoList(reader.getBorrows())
        );
    }

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> readerList) {
        return readerList.stream()
                .map(this::mapToReaderDto)
                .collect(Collectors.toList());
    }

    public List<Reader> mapToReaderList(final List<ReaderDto> readerDtoList) {
        return readerDtoList.stream()
                .map(this::mapToReader)
                .collect(Collectors.toList());
    }
}
