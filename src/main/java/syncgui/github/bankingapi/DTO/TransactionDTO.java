package syncgui.github.bankingapi.DTO;

import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class TransactionDTO {

    @Min(0)
    private Double amount;

    private UUID destinationAccount;
}
