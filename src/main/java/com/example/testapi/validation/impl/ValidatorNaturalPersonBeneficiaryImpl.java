package com.example.testapi.validation.impl;

import com.example.testapi.model.dto.RequestMoneyTransfersDto;
import com.example.testapi.model.dto.TaxReliefDto;
import com.example.testapi.model.enums.BeneficiaryType;
import com.example.testapi.validation.IValidatorNaturalPersonBeneficiary;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidatorNaturalPersonBeneficiaryImpl implements ConstraintValidator<IValidatorNaturalPersonBeneficiary, RequestMoneyTransfersDto> {
    @Override
    public boolean isValid(RequestMoneyTransfersDto requestMoneyTransfersDto, ConstraintValidatorContext constraintValidatorContext) {


        if(requestMoneyTransfersDto.getTaxRelief().getBeneficiaryType().equals(BeneficiaryType.LEGAL_PERSON)){
            return requestMoneyTransfersDto.getTaxRelief().getNaturalPersonBeneficiary() != null;
        }

        return true;

    }
}
