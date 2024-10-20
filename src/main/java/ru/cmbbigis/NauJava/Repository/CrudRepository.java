package ru.cmbbigis.NauJava.Repository;

import java.util.List;

public interface CrudRepository<T, ID> {
    void create(T entity);
    T read(ID id);
    void update(T entity) throws Exception;
    void delete(ID id);
    List<T> findAll();
}
