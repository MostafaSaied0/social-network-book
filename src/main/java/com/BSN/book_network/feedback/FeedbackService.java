package com.BSN.book_network.feedback;

import com.BSN.book_network.book.Book;
import com.BSN.book_network.book.BookRepository;
import com.BSN.book_network.common.PageResponse;
import com.BSN.book_network.exception.OperationNotPermittedException;
import com.BSN.book_network.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final BookRepository bookRepository;
    private final FeedbackMapper feedbackMapper;
    public Integer save(FeedbackRequest request , Authentication connectedUser){
        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(()->new EntityNotFoundException("No book found with ID:: " + request.bookId()));
        if (book.isArchived() || book.isShareable()){
            throw new OperationNotPermittedException("You can't give your feedback");
        }
        User user = ((User) connectedUser.getPrincipal());
        if (Objects.equals(book.getCreatedBy() , connectedUser.getName()))
            throw new OperationNotPermittedException("You can't give your feedback");

        Feedback feedback = feedbackMapper.toFeedback(request);
        return feedbackRepository.save(feedback).getId();
    }

    @Transactional
    public PageResponse<FeedbackResponse>findAllFeedbacksByBook (Integer bookId , int page , int size , Authentication connectedUser) {
        Pageable pageable = PageRequest.of(page , size);
        User user = ((User) connectedUser.getPrincipal());
        Page<Feedback> feedbacks = feedbackRepository.findAllByBookId(bookId , pageable);
        List<FeedbackResponse>feedbackResponses = feedbacks
                .stream()
                .map(f-> feedbackMapper.feedbackResponse(f, user.getId()))
                .toList();
        return new PageResponse<>(
                feedbackResponses,
                feedbacks.getNumber(),
                feedbacks.getSize(),
                feedbacks.getTotalElements(),
                feedbacks.getTotalPages(),
                feedbacks.isFirst(),
                feedbacks.isLast()
        );

    }
}
