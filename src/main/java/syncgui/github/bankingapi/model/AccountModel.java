package syncgui.github.bankingapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public @Data class AccountModel {

    @Id
    @GeneratedValue()
    private UUID accountNumber;

    private String accountHolder;

    private Double balance;
}
