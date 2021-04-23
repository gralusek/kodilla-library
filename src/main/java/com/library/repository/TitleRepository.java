package com.library.repository;

import com.library.domain.Title;
import org.springframework.data.repository.CrudRepository;

public interface TitleRepository extends CrudRepository<Title, Long> {

    Title save(Title title);
}
