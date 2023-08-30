package syncgui.github.bankingapi.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Entity
@Builder
@Data
public class TransactionModel {

    @Id
    @GeneratedValue()
    private UUID transactionId;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private Double amount;

    private UUID sourceAccount;

    private UUID destinationAccount;
}
