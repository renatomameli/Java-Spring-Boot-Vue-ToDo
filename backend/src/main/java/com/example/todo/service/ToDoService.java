package com.example.todo.service;

import com.example.todo.model.ToDo;
import com.example.todo.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoRepository toDoRepository;

    public List<ToDo> getToDos() {
        return this.toDoRepository.findAll();
    }

    public void saveToDo(final String description) {
        ToDo toDo = new ToDo();
        toDo.setDescription(description);
        this.toDoRepository.save(toDo);
    }

    public void toggleDone(final Long id) {
        ToDo toDo = this.toDoRepository.getReferenceById(id);
        toDo.setDone(!toDo.isDone());
        this.toDoRepository.save(toDo);
    }

    public void deleteToDo(final Long id) {
        this.toDoRepository.deleteById(id);
    }
}
