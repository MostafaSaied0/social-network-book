package com.BSN.book_network.common;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @GeneratedValue
private Integer id;
    @CreatedDate
    @Column(nullable = false , updatable = false)
private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(insertable = false)
private LocalDateTime lastModifiedDate;
    @CreatedBy
    @Column(nullable = false,updatable = false)
private String createdBy;
    @LastModifiedDate
    @Column(insertable = false)
private String lastModifiedBy;

}
