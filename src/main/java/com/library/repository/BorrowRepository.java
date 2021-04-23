package com.library.repository;

import com.library.domain.Borrow;
import org.springframework.data.repository.CrudRepository;

public interface BorrowRepository extends CrudRepository<Borrow, Long> {

    Borrow save (Borrow borrow);


}
