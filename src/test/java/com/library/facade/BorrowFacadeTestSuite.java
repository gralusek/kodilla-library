package com.library.facade;

import com.library.domain.Title;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BorrowFacadeTestSuite {

    @Mock
    private BorrowFacade borrowFacade;

    @Test
    void getTitlesByPartOfName() throws BorrowProcessException {
        //Given
        Title title1 = new Title(1L, "test1", "author",
                LocalDate.of(2020,1,1), new ArrayList<>());
        Title title2 = new Title(1L, "test2", "author",
                LocalDate.of(2020,1,1), new ArrayList<>());
        List<Title> titles = new ArrayList<>();
        titles.add(title1);
        titles.add(title2);

        //When
        when(borrowFacade.getTitlesByPartOfName("test")).thenReturn(titles);

        //Then
        assertEquals(2, borrowFacade.getTitlesByPartOfName("test").size());
    }
}