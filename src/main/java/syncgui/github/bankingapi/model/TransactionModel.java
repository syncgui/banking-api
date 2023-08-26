package syncgui.github.bankingapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.util.UUID;

@Entity
public @Data class TransactionModel {

    @Id
    @GeneratedValue()
    private UUID transactionId;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Min(0)
    private Double amount;

    private UUID sourceAccount;

    private UUID destinationAccount;
}
