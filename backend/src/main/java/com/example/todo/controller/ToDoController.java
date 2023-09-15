package com.example.todo.controller;

import com.example.todo.model.ToDo;
import com.example.todo.model.ValidationResult;
import com.example.todo.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/to-dos")
@CrossOrigin(origins = "http://localhost:8081")
@RequiredArgsConstructor
public class ToDoController {
    private final ToDoService toDoService;

    @GetMapping
    public ResponseEntity<List<ToDo>> getToDos() {
        return ResponseEntity.ok(this.toDoService.getToDos());
    }

    @PostMapping
    public ResponseEntity<?> saveToDo(@RequestBody ToDo toDo) {
        ValidationResult validationResult = this.toDoService.saveToDo(toDo);
        if (validationResult.isNotValid()) {
            return new ResponseEntity<>(validationResult.getErrorMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ValidationResult(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> toggleDone(@PathVariable Long id) {
        this.toDoService.toggleDone(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable Long id) {
        try {
            this.toDoService.deleteToDo(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
