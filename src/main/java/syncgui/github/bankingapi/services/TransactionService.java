package syncgui.github.bankingapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import syncgui.github.bankingapi.dtos.AccountDTO;
import syncgui.github.bankingapi.dtos.TransactionDTO;
import syncgui.github.bankingapi.mappers.AccountMapper;
import syncgui.github.bankingapi.mappers.TransactionMapper;
import syncgui.github.bankingapi.models.AccountModel;
import syncgui.github.bankingapi.models.TransactionModel;
import syncgui.github.bankingapi.models.TransactionType;
import syncgui.github.bankingapi.repositories.AccountRepository;
import syncgui.github.bankingapi.repositories.TransactionRepository;

import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountService accountService;

    public AccountDTO deposit(UUID uuid, TransactionDTO transactionDTO) {
        TransactionModel transactionModel = TransactionMapper.toModel(transactionDTO);

        accountService.sumBalance(uuid, transactionDTO.getAmount());

        transactionModel.setDestinationAccount(uuid);
        transactionModel.setTransactionType(TransactionType.DEPOSIT);

        transactionRepository.save(transactionModel);

        AccountModel account = accountRepository.findById(uuid).get();

        return AccountMapper.toDTO(account);
    }

    public AccountDTO withdraw(UUID uuid, TransactionDTO transactionDTO) {
        TransactionModel transactionModel = TransactionMapper.toModel(transactionDTO);

        accountService.subtractBalance(uuid, transactionDTO.getAmount());

        transactionModel.setSourceAccount(uuid);
        transactionModel.setTransactionType(TransactionType.WITHDRAW);

        transactionRepository.save(transactionModel);

        AccountModel account = accountRepository.findById(uuid).get();

        return AccountMapper.toDTO(account);
    }

    public AccountDTO transfer(UUID sourceUUID, TransactionDTO transactionDTO) {
        TransactionModel transactionModel = TransactionMapper.toModel(transactionDTO);

        accountService.subtractBalance(sourceUUID, transactionDTO.getAmount());
        accountService.sumBalance(transactionDTO.getDestinationAccount(), transactionDTO.getAmount());

        transactionModel.setSourceAccount(sourceUUID);
        transactionModel.setTransactionType(TransactionType.TRANSFER);

        transactionRepository.save(transactionModel);

        AccountModel account = accountRepository.findById(sourceUUID).get();

        return AccountMapper.toDTO(account);
    }
}
