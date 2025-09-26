package com.example.testapi.validation;


import com.example.testapi.validation.impl.ValidatorNaturalPersonBeneficiaryImpl;
import jakarta.validation.Constraint;

@Constraint(validatedBy = ValidatorNaturalPersonBeneficiaryImpl.class)
public @interface IValidatorNaturalPersonBeneficiary {

    String message() default "Il campo NaturalPersonBeneficiary è obbligatorio se beneficiaryType è LEGAL_PERSON";

}
