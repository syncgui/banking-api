package syncgui.github.bankingapi.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syncgui.github.bankingapi.dtos.AccountDTO;
import syncgui.github.bankingapi.exceptions.AccountNotFoundException;
import syncgui.github.bankingapi.mappers.AccountMapper;
import syncgui.github.bankingapi.repositories.AccountRepository;

import java.util.List;
import java.util.UUID;

import static syncgui.github.bankingapi.mappers.AccountMapper.toDTO;
import static syncgui.github.bankingapi.mappers.AccountMapper.toModel;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @PersistenceContext
    EntityManager entityManager;

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
        return ResponseEntity.noContent().build();
    }

    public void accountExists(UUID uuid) {
        if (!(accountRepository.checkIfAccountExists(uuid) > 0)) {
            throw new AccountNotFoundException(uuid);
        }
    }
}
