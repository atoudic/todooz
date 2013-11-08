package fr.todooz.service;

import fr.todooz.domain.Task;

import java.util.List;

public interface TaskService {
    void save(Task task);

    void delete(Long id);

    List<Task> findAll();

    List<Task> findByQuery(String query);

    List<Task> findByTag(String tag);

    int count();

    Task findById(Long id);
}
