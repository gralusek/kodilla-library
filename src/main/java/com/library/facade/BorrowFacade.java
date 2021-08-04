package com.library.facade;

import com.library.domain.Book;
import com.library.domain.BookStatus;
import com.library.domain.Title;
import com.library.mapper.BookMapper;
import com.library.mapper.BorrowMapper;
import com.library.mapper.ReaderMapper;
import com.library.mapper.TitleMapper;
import com.library.service.BookService;
import com.library.service.BorrowService;
import com.library.service.ReaderService;
import com.library.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowFacade {

    @Autowired
    BookMapper bookMapper;

    @Autowired
    BorrowMapper borrowMapper;

    @Autowired
    ReaderMapper readerMapper;

    @Autowired
    TitleMapper titleMapper;

    @Autowired
    BorrowService borrowService;

    @Autowired
    TitleService titleService;

    @Autowired
    BookService bookService;

    @Autowired
    ReaderService readerService;

    public List<Title> getTitlesByPartOfName(String partOfName) throws BorrowProcessException{
        List<Title> titles = titleService.findByPartOfName(partOfName);
        if( titles.size() == 0) {
            throw new BorrowProcessException(BorrowProcessException.TITLE_NOT_FOUND);
        }
        System.out.println(titles.size() + " titles have been found");
        return titles;
    }
}
