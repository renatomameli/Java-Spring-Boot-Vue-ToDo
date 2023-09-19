package com.example.todo.service;

import com.example.todo.model.User;
import com.example.todo.model.ToDo;
import com.example.todo.model.ValidationResult;
import com.example.todo.repository.ToDoRepository;
import com.mysql.cj.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoRepository toDoRepository;
    private final UserService userService;

    public List<ToDo> getToDos() {
        User currentUser = this.userService.getCurrentUser();
        List<ToDo> toDos = this.toDoRepository.findAllByUser(currentUser);
        this.sortToDosByDeadline(toDos);
        return toDos;
    }

    public ValidationResult saveToDo(final ToDo toDo) {
        User currentUser = this.userService.getCurrentUser();
        toDo.setUser(currentUser);
        ValidationResult validationResult = this.validateToDo(toDo);
        if (validationResult.isNotValid()) {
            return validationResult;
        }
        this.toDoRepository.save(toDo);
        return new ValidationResult();
    }

    public void toggleDone(final Long id) {
        ToDo toDo = this.toDoRepository.getReferenceById(id);
        User currentUser = this.userService.getCurrentUser();
        if (!Objects.equals(currentUser.getId(), toDo.getUser().getId())) {
            return;
        }
        toDo.setDone(!toDo.isDone());
        this.toDoRepository.save(toDo);
    }

    public void deleteToDo(final Long id) {
        User currentUser = this.userService.getCurrentUser();
        ToDo toDo = this.toDoRepository.findById(id).orElseThrow();
        if (!Objects.equals(currentUser.getId(), toDo.getUser().getId())) {
            return;
        }
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

    private void sortToDosByDeadline(List<ToDo> toDos) {
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
