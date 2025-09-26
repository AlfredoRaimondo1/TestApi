package com.example.testapi.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NaturalPersonBeneficiaryDto {
    @NotNull
    private String fiscalCode1;
    private String fiscalCode2;
    private String fiscalCode3;
    private String fiscalCode4;
    private String fiscalCode5;

    public NaturalPersonBeneficiaryDto(String fiscalCode1, String fiscalCode2, String fiscalCode3, String fiscalCode4, String fiscalCode5) {
        this.fiscalCode1 = fiscalCode1;
        this.fiscalCode2 = fiscalCode2;
        this.fiscalCode3 = fiscalCode3;
        this.fiscalCode4 = fiscalCode4;
        this.fiscalCode5 = fiscalCode5;
    }
}
