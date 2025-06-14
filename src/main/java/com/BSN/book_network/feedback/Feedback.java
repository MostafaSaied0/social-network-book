package com.BSN.book_network.feedback;

import com.BSN.book_network.book.Book;
import com.BSN.book_network.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Feedback extends BaseEntity {
    @Column
private Double note;
private String comment;

@ManyToOne
@JoinColumn(name = "book_id")
private Book book;
}
