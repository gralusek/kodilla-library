package com.library.mapper;

import com.library.Dto.BorrowDto;
import com.library.domain.Borrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowMapper {

    @Autowired
    ReaderMapper readerMapper;

    @Autowired
    BookMapper bookMapper;

    public Borrow mapToBorrow(final BorrowDto borrowDto) {
        return new Borrow(
                borrowDto.getId(),
                readerMapper.mapToReader(borrowDto.getReaderDto()),
                borrowDto.getDateOfRent(),
                borrowDto.getDateOfReturn(),
                bookMapper.mapToBook(borrowDto.getBookDto())
        );
    }

    public BorrowDto mapToBorrowDto(final Borrow borrow) {
        return new BorrowDto(
                borrow.getId(),
                readerMapper.mapToReaderDto(borrow.getReader()),
                borrow.getDateOfRent(),
                borrow.getDateOfReturn(),
                bookMapper.mapToBookDto(borrow.getBook())
        );
    }

    public List<Borrow> mapToBorrowList(final List<BorrowDto> borrowDtoList){
        return borrowDtoList.stream()
                .map(this::mapToBorrow)
                .collect(Collectors.toList());
    }

    public List<BorrowDto> mapToBottowDtoList(final List<Borrow> borrowList) {
        return borrowList.stream()
                .map(this::mapToBorrowDto)
                .collect(Collectors.toList());
    }
}
