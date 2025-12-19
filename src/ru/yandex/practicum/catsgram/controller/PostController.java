package ru.yandex.practicum.catsgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exception.ParameterNotValidException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.SortOrder;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public Optional<Post> findPostById(@PathVariable Long id) {
        return postService.findPostById(id);
    }

    @GetMapping
    public Collection<Post> findAll(@RequestParam(defaultValue = "desc") String sort,
                                    @RequestParam(defaultValue = "0") int from,
                                    @RequestParam(defaultValue = "10") int size) {

        SortOrder sortOrder = SortOrder.from(sort);
        if (sortOrder == null) {
            throw new ParameterNotValidException("sort",
                    String.format("Получено: %s должно быть: ascending, asc, descending или desc", sort));
        }

        if (size <= 0) {
            throw new ParameterNotValidException("size",
                    "Размер должен быть больше нуля");
        }

        if (from < 0) {
            throw new ParameterNotValidException("from",
                    "Начало выборки должно быть положительным числом");
        }

        return postService.findAll(SortOrder.from(sort), from, size);
    }

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @PutMapping
    public Post update(@RequestBody Post newPost) {
        return postService.update(newPost);
    }
}