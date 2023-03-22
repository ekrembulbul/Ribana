package com.bulbul.ribana.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Transactional
public interface BaseService<T, ID> {
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<T> findAll();

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<T> findAll(Sort sort);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    T findById(ID id) throws NoSuchElementException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<T> findAllByIdList(List<ID> idList);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Long count();

    T create(T entity);

    List<T> createAll(List<T> entityList);

    T update(ID id, T entity) throws EntityNotFoundException;

    List<T> updateAll(List<T> entityList);

    void delete(ID id) throws EntityNotFoundException;

    void deleteAllByIdList(List<ID> idList);

    void deleteAll();
}
