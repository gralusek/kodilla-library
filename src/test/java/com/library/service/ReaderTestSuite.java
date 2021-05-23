package com.library.service;

import com.library.domain.Reader;
import com.library.repository.ReaderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReaderTestSuite {

    @Autowired
    ReaderRepository readerRepository;

    @Test
    void testAddReader() {
        //Given
        Reader reader = new Reader();

        //When
        readerRepository.save(reader);

        //Then
        assertTrue(readerRepository.existsById(reader.getId()));
    }
}
