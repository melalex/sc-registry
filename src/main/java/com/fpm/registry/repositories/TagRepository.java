package com.fpm.registry.repositories;

import com.fpm.registry.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.stream.Stream;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Stream<Tag> streamDistinctByNameIn(Collection<String> names);

    Page<Tag> findAllByNameStartingWith(String name, Pageable pageable);
}
