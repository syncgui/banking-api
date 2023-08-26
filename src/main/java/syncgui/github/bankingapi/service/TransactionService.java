package syncgui.github.bankingapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import syncgui.github.bankingapi.DTO.AccountDTO;
import syncgui.github.bankingapi.DTO.TransactionDTO;
import syncgui.github.bankingapi.mapper.AccountMapper;
import syncgui.github.bankingapi.mapper.TransactionMapper;
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
