package com.example.testapi.model.dto;

import com.example.testapi.model.enums.FeeType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class RequestMoneyTransfersDto {
    @NotNull
    @Valid
    private CreditorDto creditor;

    private Date executionDate;

    private String uri;

    @NotNull
    @Size(max = 140)
    private String description;

    @NotNull
    private Number amount;

    @NotNull
    private String currency;

    private Boolean isUrgent;

    private Boolean isInstant;

    @Valid
    private FeeType feeType;

    private String feeAccountId;

    @Valid
    private TaxReliefDto taxRelief;

    public RequestMoneyTransfersDto(@NotNull CreditorDto creditor, Date executionDate, String uri, @NotNull String description, @NotNull Number amount, @NotNull String currency, Boolean isUrgent, Boolean isInstant, FeeType feeType, String feeAccountId, TaxReliefDto taxRelief) {
        this.creditor = creditor;
        this.executionDate = executionDate;
        this.uri = uri;
        this.description = description;
        this.amount = amount;
        this.currency = currency;
        this.isUrgent = isUrgent;
        this.isInstant = isInstant;
        this.feeType = feeType;
        this.feeAccountId = feeAccountId;
        this.taxRelief = taxRelief;
    }



    @AssertTrue(message = "feeType deve essere uno tra: SHA, OUR, OUR")
    public boolean isFeeTypeValid() {
        if(feeType != null){
            return FeeType.isValid(feeType);
        }
        return true;
    }

}
