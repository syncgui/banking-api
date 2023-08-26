package syncgui.github.bankingapi.mapper;

import syncgui.github.bankingapi.DTO.AccountDTO;
import syncgui.github.bankingapi.model.AccountModel;

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
