package com.marcioviana.todolist.repository;

import com.marcioviana.todolist.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
