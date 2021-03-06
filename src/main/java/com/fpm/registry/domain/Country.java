package com.fpm.registry.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "country", indexes = @Index(name = "name_index", columnList = "name", unique = true))
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 3)
    private String iso;

    @Column(length = 40)
    private String name;
}
