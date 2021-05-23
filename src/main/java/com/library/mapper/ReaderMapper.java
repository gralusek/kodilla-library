package com.library.mapper;

import com.library.Dto.ReaderDto;
import com.library.domain.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                borrowMapper.mapToBottowDtoList(reader.getBorrows())
        );
    }
}
