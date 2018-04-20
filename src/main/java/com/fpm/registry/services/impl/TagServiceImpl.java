package com.fpm.registry.services.impl;

import com.fpm.registry.domain.Tag;
import com.fpm.registry.repositories.TagRepository;
import com.fpm.registry.services.TagService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    @Override
    public Set<Tag> getByNames(Collection<String> names) {
        return tagRepository.streamDistinctByNameIn(names)
                .collect(Collectors.toSet());
    }

    @Override
    public Page<Tag> getByNameStartsWith(String name, Pageable pageable) {
        return tagRepository.findAllByNameStartingWith(name, pageable);
    }
}
