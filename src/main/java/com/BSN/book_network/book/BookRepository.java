package com.BSN.book_network.book;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;



public interface BookRepository extends JpaRepository<Book , Integer> , JpaSpecificationExecutor<Book> {
    @Query(""" 
            SELECT book FROM Book book
            WHERE book.archived = false
            AND book.shareable = true
            And book.createdBy != :userId
            """)
    Page<Book> findAllDisplayableBooks(Pageable pageable, String userId);
}

