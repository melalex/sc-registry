package com.fpm.registry.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "document", indexes = @Index(name = "code_index", columnList = "code"))
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status = Status.INITIAL;

    @Column(length = 40)
    private String code;

    @Column
    private String description;

    @CreatedDate
    @Column(updatable = false)
    private LocalDate date;

    @CreatedBy
    @ManyToOne(fetch = FetchType.EAGER)
    private User employee;

    @ManyToOne(fetch = FetchType.EAGER)
    private Place place;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "document", cascade = CascadeType.ALL)
    private Media attachment;

    public enum Status {
        INITIAL,
        ACTIVE
    }
}
