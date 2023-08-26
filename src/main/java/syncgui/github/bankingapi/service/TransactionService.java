package syncgui.github.bankingapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import syncgui.github.bankingapi.model.AccountModel;
import syncgui.github.bankingapi.model.TransactionModel;
import syncgui.github.bankingapi.model.TransactionType;
import syncgui.github.bankingapi.repository.AccountRepository;
import syncgui.github.bankingapi.repository.TransactionRepository;

import java.util.UUID;
@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountService accountService;

    public AccountModel deposit(UUID uuid, TransactionModel transactionModel) {
        AccountModel accountModel = accountService.getAccountByUUID(uuid);

        accountService.sumBalance(accountModel, transactionModel.getAmount());

        transactionModel.setDestinationAccount(uuid);
        transactionModel.setTransactionType(TransactionType.DEPOSIT);

        accountRepository.save(accountModel);
        transactionRepository.save(transactionModel);

        return accountModel;
    }

    public AccountModel withdraw(UUID uuid, TransactionModel transactionModel) {
        AccountModel accountModel = accountService.getAccountByUUID(uuid);

        accountService.subtractBalance(accountModel, transactionModel.getAmount());

        transactionModel.setSourceAccount(uuid);
        transactionModel.setTransactionType(TransactionType.WITHDRAW);

        accountRepository.save(accountModel);
        transactionRepository.save(transactionModel);

        return accountModel;
    }

    public AccountModel[] transfer(UUID sourceUUID, UUID destinationUUID, TransactionModel transactionModel) {
        AccountModel sourceAccountModel = accountService.getAccountByUUID(sourceUUID);
        AccountModel destinationAccountModel = accountService.getAccountByUUID(destinationUUID);

        accountService.subtractBalance(sourceAccountModel, transactionModel.getAmount());
        accountService.sumBalance(destinationAccountModel, transactionModel.getAmount());

        transactionModel.setSourceAccount(sourceUUID);
        transactionModel.setDestinationAccount(destinationUUID);
        transactionModel.setTransactionType(TransactionType.TRANSFER);

        accountRepository.save(sourceAccountModel);
        accountRepository.save(destinationAccountModel);
        transactionRepository.save(transactionModel);

        return new AccountModel[]{sourceAccountModel, destinationAccountModel};
    }
}
