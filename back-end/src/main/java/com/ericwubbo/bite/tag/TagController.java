package com.ericwubbo.bite.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tags")
@RequiredArgsConstructor
public class TagController {

    private final TagRepository tagRepository;
    @GetMapping
    public Iterable<Tag> getAll() { return tagRepository.findAll(); }
}
