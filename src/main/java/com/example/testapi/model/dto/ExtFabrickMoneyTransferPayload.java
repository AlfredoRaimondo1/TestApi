package com.example.testapi.model.dto;

import com.example.testapi.model.enums.FeeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ExtFabrickMoneyTransferPayload {
    private String moneyTransferId;
    private String status;
    private String direction;
    private CreditorDto creditor;
    private DebtorDto debtor;
    private String cro;
    private String trn;
    private String uri;
    private String descprition;
    private Date createdDatetime;
    private Date accountedDatetime;
    private Date debtorValueDate;
    private AmountDto amount;
    private Boolean isUrgent;
    private Boolean isInstant;
    private FeeType feeType;
    private String feeAccountId;
    private List<FeeDto> fees;
    private Boolean hasTaxRelief;

    public ExtFabrickMoneyTransferPayload(String moneyTransferId, String status, String direction, CreditorDto creditor, DebtorDto debtor, String cro, String trn, String uri, String descprition, Date createdDatetime, Date accountedDatetime, Date debtorValueDate, AmountDto amount, Boolean isUrgent, Boolean isInstant, FeeType feeType, String feeAccountId, List<FeeDto> fees, Boolean hasTaxRelief) {
        this.moneyTransferId = moneyTransferId;
        this.status = status;
        this.direction = direction;
        this.creditor = creditor;
        this.debtor = debtor;
        this.cro = cro;
        this.trn = trn;
        this.uri = uri;
        this.descprition = descprition;
        this.createdDatetime = createdDatetime;
        this.accountedDatetime = accountedDatetime;
        this.debtorValueDate = debtorValueDate;
        this.amount = amount;
        this.isUrgent = isUrgent;
        this.isInstant = isInstant;
        this.feeType = feeType;
        this.feeAccountId = feeAccountId;
        this.fees = fees;
        this.hasTaxRelief = hasTaxRelief;
    }
}
