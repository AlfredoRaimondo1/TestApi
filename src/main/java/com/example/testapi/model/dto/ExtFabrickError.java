package com.example.testapi.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExtFabrickError {
    private String code;
    private String description;
    private String params;

    public ExtFabrickError(String code, String description, String params) {
        this.code = code;
        this.description = description;
        this.params = params;
    }
}
