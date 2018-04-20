package com.fpm.registry.repositories;

import com.fpm.registry.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Set;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Set<Tag> findAllByNameIn(Collection<String> names);

    Page<Tag> findAllByNameStartingWith(String name, Pageable pageable);
}
