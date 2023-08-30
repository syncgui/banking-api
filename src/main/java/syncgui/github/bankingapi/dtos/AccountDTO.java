package syncgui.github.bankingapi.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDTO {

    @NotBlank
    private String accountHolder;
    private Double balance;

}
