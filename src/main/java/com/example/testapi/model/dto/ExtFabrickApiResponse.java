package com.example.testapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ExtFabrickApiResponse<P> {

    @JsonProperty("status")
    private String status;

    @JsonProperty("errors")
    private List<ExtFabrickError> errors;

    @JsonProperty("payload")
    private P payload;

    public ExtFabrickApiResponse(String status, List<ExtFabrickError> errors, P payload) {
        this.status = status;
        this.errors = errors;
        this.payload = payload;
    }
}
