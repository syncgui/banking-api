package syncgui.github.bankingapi.models;

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
@Data
public class AccountModel {

    @Id
    @GeneratedValue()
    private UUID accountNumber;

    private String accountHolder;

    private Double balance;
}
