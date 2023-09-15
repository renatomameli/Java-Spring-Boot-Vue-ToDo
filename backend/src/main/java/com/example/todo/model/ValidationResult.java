package com.example.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationResult {
    private boolean isValid = true;
    private String errorMessage;

    public boolean isNotValid() {
        return !this.isValid;
    }
}
