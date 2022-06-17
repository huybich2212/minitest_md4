package service;

import model.Post;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<Post> findAll();
    Optional<T> findById(Long id);
    void save (T t);
    void remove(Long id);
}
