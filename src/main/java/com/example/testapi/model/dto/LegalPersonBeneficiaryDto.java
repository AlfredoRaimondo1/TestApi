package com.example.testapi.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LegalPersonBeneficiaryDto {
    @NotNull
    private String fiscalCode;
    private String legalRepresentativeFiscalCode;

    public LegalPersonBeneficiaryDto(String fiscalCode, String legalRepresentativeFiscalCode) {
        this.fiscalCode = fiscalCode;
        this.legalRepresentativeFiscalCode = legalRepresentativeFiscalCode;
    }
}
