package com.example.testapi.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InternalCodesError {
    private String code;
    private String description;

    public InternalCodesError(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
