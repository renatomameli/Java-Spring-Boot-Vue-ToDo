package com.example.todo.service;

import com.example.todo.model.ToDo;
import com.example.todo.model.ValidationResult;
import com.example.todo.repository.ToDoRepository;
import com.mysql.cj.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoRepository toDoRepository;

    public List<ToDo> getToDos() {
        List<ToDo> toDos = this.toDoRepository.findAll();
        this.sortToDosByDeadline(toDos);
        return toDos;
    }

    public ValidationResult saveToDo(final ToDo toDo) {
        ValidationResult validationResult = this.validateToDo(toDo);
        if (validationResult.isNotValid()) {
            return validationResult;
        }
        this.toDoRepository.save(toDo);
        return new ValidationResult();
    }

    public void toggleDone(final Long id) {
        ToDo toDo = this.toDoRepository.getReferenceById(id);
        toDo.setDone(!toDo.isDone());
        this.toDoRepository.save(toDo);
    }

    public void deleteToDo(final Long id) {
        this.toDoRepository.deleteById(id);
    }

    private ValidationResult validateToDo(ToDo toDo) {
        if (this.toToHasEmptyDescription(toDo)) {
            return new ValidationResult(false, "A To Do needs a description");
        }

        if (this.validateIfDeadlineIsNotBeforeToday(toDo)) {
            return new ValidationResult(false, "The deadline date must be in the future");
        }

        return new ValidationResult();
    }

    private boolean toToHasEmptyDescription(final ToDo toDo) {
        return StringUtils.isNullOrEmpty(toDo.getDescription());
    }

    private boolean validateIfDeadlineIsNotBeforeToday(final ToDo toDo) {
        Date today = Calendar.getInstance().getTime();
        return (toDo.getDeadline() != null && toDo.getDeadline().before(today));
    }

    public void sortToDosByDeadline(List<ToDo> toDos) {
        toDos.sort((toDo1, toDo2) -> {
            if (toDo1.getDeadline() == null && toDo2.getDeadline() == null) {
                return 0;
            }
            if (toDo1.getDeadline() == null) {
                return 1;
            }
            if (toDo2.getDeadline() == null) {
                return -1;
            }
            return toDo1.getDeadline().compareTo(toDo2.getDeadline());
        });
    }
}
