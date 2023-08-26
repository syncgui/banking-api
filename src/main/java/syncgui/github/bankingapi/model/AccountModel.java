package syncgui.github.bankingapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Entity
public @Data class AccountModel {

    @Id
    @GeneratedValue()
    private UUID accountNumber;
    @NotBlank
    private String accountHolder;

    private Double balance;
}
