package com.entrega.dao;

import java.util.List;

/**
 * Interfaz con la que gestionaremos el proceso del crud.
 * @param <T> genérico pero en este caso Movie
 */
public interface DAO<T> {
    List<T> findAll();
    T findById(Integer id);
    void save(T t);
    void update(T t);
    void delete(T t);
    void deleteById(Integer id);
}
