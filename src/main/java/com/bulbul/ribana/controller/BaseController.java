package com.bulbul.ribana.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BaseController<T, ID> {
    @GetMapping
    ResponseEntity<List<T>> findAll();

    @GetMapping("/sort")
    ResponseEntity<List<T>> findAll(@RequestParam String direction, @RequestParam String... properties);

    @GetMapping("/{id}")
    ResponseEntity<T> findById(@PathVariable ID id);

    @GetMapping("/idList")
    ResponseEntity<List<T>> findByIdList(@RequestParam List<ID> idList);

    @GetMapping("/count")
    ResponseEntity<Long> count();

    @PostMapping
    ResponseEntity<T> create(@RequestBody T entity);

    @PostMapping("/all")
    ResponseEntity<List<T>> createAll(@RequestBody List<T> entityList);

    @PutMapping("/{id}")
    ResponseEntity<T> update(@PathVariable ID id, @RequestBody T entity);

    @PutMapping("/all")
    ResponseEntity<List<T>> updateAll(@RequestBody List<T> entityList);

    @DeleteMapping("/{id}")
    ResponseEntity<T> delete(@PathVariable ID id);

    @DeleteMapping("/idList")
    ResponseEntity<T> deleteAllByIdList(@RequestParam List<ID> idList);

    @DeleteMapping("/all")
    ResponseEntity<T> deleteAll();
}
