package com.library.repository;

import com.library.domain.Reader;
import org.springframework.data.repository.CrudRepository;

public interface ReaderRepository extends CrudRepository<Reader, Long> {

    Reader save(Reader reader);
}
