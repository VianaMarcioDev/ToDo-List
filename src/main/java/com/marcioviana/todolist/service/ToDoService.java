package com.marcioviana.todolist.service;

import com.marcioviana.todolist.entity.ToDo;
import com.marcioviana.todolist.repository.ToDoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {


    private ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<ToDo> create(ToDo toDo){
        toDoRepository.save(toDo);
        return list();
    }
    public List<ToDo> list(){
        Sort sort = Sort.by("prioridades").descending().descending().
                and(Sort.by("nome").ascending());
        return toDoRepository.findAll(sort);
    }
    public List<ToDo> update(ToDo toDo){
        toDoRepository.save(toDo);
        return list();
    }
    public List<ToDo> delete(Long id){
        toDoRepository.deleteById(id);
        return list();
    }



}
