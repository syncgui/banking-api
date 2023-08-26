package syncgui.github.bankingapi.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syncgui.github.bankingapi.DTO.AccountDTO;
import syncgui.github.bankingapi.exception.AccountNotFoundException;
import syncgui.github.bankingapi.mapper.AccountMapper;
import syncgui.github.bankingapi.model.AccountModel;
import syncgui.github.bankingapi.repository.AccountRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static syncgui.github.bankingapi.mapper.AccountMapper.toDTO;
import static syncgui.github.bankingapi.mapper.AccountMapper.toModel;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @PersistenceContext
    EntityManager entityManager;

    public AccountDTO getAccountByUUID(UUID uuid) {
        Optional<AccountModel> resultAccount = accountRepository.findById(uuid);
        return toDTO(resultAccount.orElseThrow(() -> new AccountNotFoundException(uuid)));
    }

    public AccountDTO saveAccount(AccountDTO accountDTO) {
        return toDTO(accountRepository.save(toModel(accountDTO)));
    }

    public void subtractBalance(UUID uuid, Double amount) {
        accountExists(uuid);
        accountRepository.saveSubtractBalance(amount, uuid);
    }

    public void sumBalance(UUID uuid, Double amount) {
        accountExists(uuid);
        accountRepository.saveSumBalance(amount, uuid);

    }

    public List<AccountDTO> findAllAccounts() {
        return accountRepository.findAll().stream().map(AccountMapper::toDTO).toList();
    }

    @Transactional
    public ResponseEntity<String> deleteAccountByUUID(UUID uuid) {
        accountExists(uuid);
        Query query = entityManager.createNativeQuery("DELETE FROM ACCOUNT_MODEL WHERE ACCOUNT_NUMBER = :uuid");
        query.setParameter("uuid", uuid);
        int deletedRows = query.executeUpdate();
        System.out.println("Rows deleted: " + deletedRows);
        return ResponseEntity.ok().build();
    }

    public boolean accountExists(UUID uuid) {
        if (!(accountRepository.checkIfAccountExists(uuid) > 0)) {
            throw new AccountNotFoundException(uuid);
        }
        return true;
    }
}
