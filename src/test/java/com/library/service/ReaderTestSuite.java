package com.library.service;

import com.library.domain.Reader;
import com.library.repository.ReaderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReaderTestSuite {

    @Autowired
    ReaderRepository readerRepository;

    @Autowired
    ReaderService readerService;

    @Test
    void testAddReader() {
        //Given
        Reader reader = new Reader();

        //When
        readerService.addReader(reader);

        //Then
        assertTrue(readerRepository.existsById(reader.getId()));

        //CleanUp
        try {
            readerRepository.deleteById(reader.getId());
        } catch (Exception e) {
            System.out.println("There were errors");
        }
    }

    @Test
    void testFindById() {
        //Given
        Reader reader = new Reader("name", "surname", new ArrayList<>());

        //When
        readerRepository.save(reader);

        //Then
        assertEquals("name", readerService.findById(reader.getId()).get().getName());

        //CleanUp
        try {
            readerRepository.deleteById(reader.getId());
        } catch (Exception e) {
            System.out.println("There were errors");
        }
    }

    @Test
    void testFindAll() {
        //Given
        Reader reader = new Reader();
        Reader reader2 = new Reader();

        //When
        readerRepository.save(reader);
        readerRepository.save(reader2);

        //Then
        assertEquals(2, readerService.findAll().size());

        //CleanUp
        try {
            readerRepository.deleteById(reader.getId());
            readerRepository.deleteById(reader2.getId());
        } catch (Exception e) {
            System.out.println("There were errors");
        }
    }

    @Test
    void testDeleteById() {
        //Given
        Reader reader = new Reader("name", "surname", new ArrayList<>());

        //When
        readerRepository.save(reader);
        assertTrue(readerRepository.existsById(reader.getId()));
        readerService.deleteById(reader.getId());

        //Then
        assertFalse(readerRepository.existsById(reader.getId()));

        //Cleanup
        //No need
    }
}
