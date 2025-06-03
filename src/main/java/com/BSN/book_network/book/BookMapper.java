package com.BSN.book_network.book;

import com.BSN.book_network.file.FileUtils;
import com.BSN.book_network.history.BookTransactionHistory;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {
    public Book toBook(BookRequest request){
        return Book.builder()
                .id(request.id())
                .title(request.title())
                .isbn(request.isbn())
                .shareable(request.shareable())
                .authorName(request.authorName())
                .synopsis(request.synopsis())
                .archived(false)
                .build();
    }

    public BookResponse toBookResponse(Book book){
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .authorName(book.getAuthorName())
                .shareable(book.isShareable())
                .synopsis(book.getSynopsis())
                .archived(book.isArchived())
                .owner(book.getOwner().fullName())
                .rate(book.getRate())
                .cover(FileUtils.readFileFromLocation(book.getBookCover()))
                .build();

    }

    public BorrowedBookResponse toBorrowedBookResponse(BookTransactionHistory history){
        return BorrowedBookResponse.builder()
                .id(history.getBook().getId())
                .title(history.getBook().getTitle())
                .isbn(history.getBook().getIsbn())
                .authorName(history.getBook().getAuthorName())
                .rate(history.getBook().getRate())
                .returned(history.isReturned())
                .returnApproved(history.isReturnApproved())
                .build();
    }
}
