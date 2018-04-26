package com.fpm.registry.services.impl;

import com.fpm.registry.domain.Tag;
import com.fpm.registry.repositories.TagRepository;
import com.fpm.registry.services.TagService;
import com.fpm.registry.utils.Tags;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.SetUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {

    private static final String GET_BY_NAME_MESSAGE = "Fetching tags by name starts with [{}]";
    private static final String NEW_TAGS_MESSAGE = "Create new tags: {}";
    private TagRepository tagRepository;

    @Override
    public Set<Tag> getByNames(Set<String> names) {
        Set<Tag> persistedTags = tagRepository.findAllByNameIn(names);
        Set<String> persistedTagsNames = Tags.getNames(persistedTags);
        SetUtils.SetView<String> newTagsNames = SetUtils.difference(names, persistedTagsNames);

        log.debug(NEW_TAGS_MESSAGE, newTagsNames);

        Set<Tag> newTags = newTagsNames
                .stream()
                .map(Tag::new)
                .collect(Collectors.toSet());

        tagRepository.saveAll(newTags);

        return SetUtils.union(persistedTags, newTags);
    }

    @Override
    public Page<Tag> getByNameStartsWith(String name, Pageable pageable) {
        log.trace(GET_BY_NAME_MESSAGE, name);
        return tagRepository.findAllByNameStartingWith(name, pageable);
    }
}
