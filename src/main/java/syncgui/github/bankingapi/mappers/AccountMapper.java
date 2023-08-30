package syncgui.github.bankingapi.mappers;

import syncgui.github.bankingapi.dtos.AccountDTO;
import syncgui.github.bankingapi.models.AccountModel;

public class AccountMapper {

    public static AccountDTO toDTO(AccountModel accountModel) {
        return AccountDTO.builder().accountHolder(accountModel.getAccountHolder())
                .balance(accountModel.getBalance()).build();
    }

    public static AccountModel toModel(AccountDTO accountDTO) {
        return AccountModel.builder().accountHolder(accountDTO.getAccountHolder())
                .balance(accountDTO.getBalance()).build();
    }
}
