package com.library.repository;

import com.library.domain.Book;
import com.library.domain.Title;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TitleRepository extends CrudRepository<Title, Long> {

    @Override
    List<Title> findAll();

    void deleteByTitle(String name);

}
