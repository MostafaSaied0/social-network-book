package com.BSN.book_network.book;

import com.BSN.book_network.common.BaseEntity;
import com.BSN.book_network.feedback.Feedback;
import com.BSN.book_network.history.BookTransactionHistory;
import com.BSN.book_network.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book extends BaseEntity {
    private String title;
    private String authorName;
    private String isbn;
    private String synopsis;
    private String bookCover;
    private boolean archived;
    private boolean shareable;

    @ManyToOne
    @JoinColumn(name = "owner_id")
        private User owner;
    @OneToMany(mappedBy = "book")
        private List<Feedback>feedbacks;
    @OneToMany(mappedBy = "book")
        private List<BookTransactionHistory>bHistories;
    @Transient
        public double getRate(){
        if (feedbacks == null || feedbacks.isEmpty()){
            return 0.0;
        }
        var rate = this.feedbacks
                .stream()
                .mapToDouble(Feedback::getNote)
                .average()
                .orElse(0.0);
        double roundRate = Math.round(rate * 10.0)/10.0;
        return roundRate;
    }


}
