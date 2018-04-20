package com.fpm.registry.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "media", indexes = @Index(name = "name_index", columnList = "name", unique = true))
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(length = 40)
    private String name;

    @Column(length = 40)
    private String originalName;

    @Column
    private String path;

    @Column(length = 40)
    private String type;

    public enum  Status {
        ACTIVE,
        DELETED
    }
}
