package com.bulbul.ribana.controller.implementation;

import com.bulbul.ribana.controller.BaseController;
import com.bulbul.ribana.service.BaseService;
import com.bulbul.ribana.util.ControllerSortUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

public abstract class BaseControllerImpl<T, ID> implements BaseController<T, ID> {

    private final BaseService<T, ID> baseService;

    public BaseControllerImpl(BaseService<T, ID> baseService) {
        this.baseService = baseService;
    }

    @Override
    public ResponseEntity<List<T>> findAll() {
        return ResponseEntity.ok(baseService.findAll());
    }

    @Override
    public ResponseEntity<List<T>> findAll(String direction, String... properties) {
        final Sort.Direction sortDirection = ControllerSortUtil.getSortDirection(direction);

        if (Objects.isNull(sortDirection))
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(baseService.findAll(Sort.by(sortDirection, properties)));
    }

    @Override
    public ResponseEntity<List<T>> findAll(Integer page, Integer size) {
        return ResponseEntity.ok(baseService.findAll(PageRequest.of(page, size)));
    }

    @Override
    public ResponseEntity<List<T>> findAll(Integer page, Integer size, String direction, String... properties) {
        final Sort.Direction sortDirection = ControllerSortUtil.getSortDirection(direction);

        if (Objects.isNull(sortDirection))
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(baseService.findAll(PageRequest.of(page, size, Sort.by(sortDirection, properties))));
    }

    @Override
    public ResponseEntity<T> findById(ID id) {
        return ResponseEntity.ok(baseService.findById(id));
    }

    @Override
    public ResponseEntity<List<T>> findByIdList(List<ID> idList) {
        return ResponseEntity.ok(baseService.findAllByIdList(idList));
    }

    @Override
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(baseService.count());
    }

    @Override
    public ResponseEntity<T> create(T entity) {
        return ResponseEntity.ok(baseService.create(entity));
    }

    @Override
    public ResponseEntity<List<T>> createAll(List<T> entityList) {
        return ResponseEntity.ok(baseService.createAll(entityList));
    }

    @Override
    public ResponseEntity<T> update(ID id, T entity) {
        return ResponseEntity.ok(baseService.update(id, entity));
    }

    @Override
    public ResponseEntity<List<T>> updateAll(List<T> entityList) {
        return ResponseEntity.ok(baseService.updateAll(entityList));
    }

    @Override
    public ResponseEntity<T> delete(ID id) {
        baseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<T> deleteAllByIdList(List<ID> idList) {
        baseService.deleteAllByIdList(idList);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<T> deleteAll() {
        baseService.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
