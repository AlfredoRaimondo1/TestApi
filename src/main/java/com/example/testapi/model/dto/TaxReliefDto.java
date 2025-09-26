package com.example.testapi.model.dto;

import com.example.testapi.model.enums.BeneficiaryType;
import com.example.testapi.validation.ICustomValidation;
import com.example.testapi.validation.IValidatorNaturalPersonBeneficiary;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Valid
public class TaxReliefDto {
    private String taxReliefId;

    @NotNull
    private Boolean isCondoUpgrade;

    @NotNull
    private String creditorFiscalCode;

    @NotNull
    private BeneficiaryType beneficiaryType;


    private NaturalPersonBeneficiaryDto naturalPersonBeneficiary;


    private LegalPersonBeneficiaryDto legalPersonBeneficiary;

    public TaxReliefDto(String taxReliefId, Boolean isCondoUpgrade, String creditorFiscalCode, BeneficiaryType beneficiaryType, NaturalPersonBeneficiaryDto naturalPersonBeneficiary, LegalPersonBeneficiaryDto legalPersonBeneficiary) {
        this.taxReliefId = taxReliefId;
        this.isCondoUpgrade = isCondoUpgrade;
        this.creditorFiscalCode = creditorFiscalCode;
        this.beneficiaryType = beneficiaryType;
        this.naturalPersonBeneficiary = naturalPersonBeneficiary;
        this.legalPersonBeneficiary = legalPersonBeneficiary;
    }


    private static final Set<String> VALID_TYPES = Set.of("119R", "DL50", "L296", "L449", "L234");
    @AssertTrue(message = "Il campo taxReliefId è opzionale ma se presente deve assumere un valore valido")
    public boolean isTaxReliefIdValid() {
        if(taxReliefId != null){
            return VALID_TYPES.contains(taxReliefId);
        }

        return true;
    }

    @AssertTrue(message = "Il campo naturalPersonBeneficiary è obbligatorio se beneficiaryType è NATURAL_PERSON")
    public boolean isNaturalPersonBeneficiaryValid() {
        if (beneficiaryType.equals(BeneficiaryType.NATURAL_PERSON)) {
            return naturalPersonBeneficiary != null;
        }
        return true;
    }

    @AssertTrue(message = "Il campo legalPersonBeneficiary è obbligatorio se beneficiaryType è LEGAL_PERSON")
    public boolean isLegalPersonBeneficiaryDtoValid() {
        if (beneficiaryType.equals(BeneficiaryType.LEGAL_PERSON)) {
            return legalPersonBeneficiary != null;
        }
        return true;
    }


}
