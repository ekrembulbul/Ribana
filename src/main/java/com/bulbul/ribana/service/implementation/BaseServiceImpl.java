package com.bulbul.ribana.service.implementation;

import com.bulbul.ribana.repository.BaseRepository;
import com.bulbul.ribana.service.BaseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.NoSuchElementException;

public abstract class BaseServiceImpl<T, ID> implements BaseService<T, ID> {

    private final BaseRepository<T, ID> baseRepository;

    public BaseServiceImpl(BaseRepository<T, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return baseRepository.findAll(sort);
    }

    @Override
    public List<T> findAll(Pageable pageable) {
        return baseRepository.findAll(pageable).getContent();
    }

    @Override
    public T findById(ID id) throws NoSuchElementException {
        return baseRepository.findById(id).orElseThrow();
    }

    @Override
    public List<T> findAllByIdList(List<ID> idList) {
        return baseRepository.findAllById(idList);
    }

    @Override
    public Long count() {
        return baseRepository.count();
    }

    @Override
    public T create(T entity) {
        return baseRepository.save(entity);
    }

    @Override
    public List<T> createAll(List<T> entityList) {
        return baseRepository.saveAll(entityList);
    }

    @Override
    public T update(ID id, T entity) throws EntityNotFoundException {
        if (baseRepository.existsById(id)) return baseRepository.save(entity);
        else throw new EntityNotFoundException();
    }

    @Override
    public List<T> updateAll(List<T> entityList) {
        return baseRepository.saveAll(entityList);
    }

    @Override
    public void delete(ID id) throws EntityNotFoundException {
        if (baseRepository.existsById(id)) baseRepository.deleteById(id);
        else throw new EntityNotFoundException();
    }

    @Override
    public void deleteAllByIdList(List<ID> idList) {
        baseRepository.deleteAllById(idList);
    }

    @Override
    public void deleteAll() {
        baseRepository.deleteAll();
    }

}
