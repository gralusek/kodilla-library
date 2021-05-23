package com.library.mapper;

import com.library.Dto.TitleDto;
import com.library.domain.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TitleMapper {

    @Autowired
    BookMapper bookMapper;

    public Title mapToTitle(final TitleDto titleDto) {
        return new Title(
                titleDto.getId(),
                titleDto.getTitle(),
                titleDto.getAuthor(),
                titleDto.getYearOfPublication(),
                bookMapper.mapToBookList(titleDto.getBooksDtoList())
        );
    }

    public TitleDto mapToTileDto(final Title title) {
        return new TitleDto(
                title.getId(),
                title.getTitle(),
                title.getAuthor(),
                title.getYearOfPublication(),
                bookMapper.mapToBookDtoList(title.getBooks())
        );
    }
}
