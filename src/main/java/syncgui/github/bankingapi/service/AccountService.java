package syncgui.github.bankingapi.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syncgui.github.bankingapi.exception.AccountNotFoundException;
import syncgui.github.bankingapi.model.AccountModel;
import syncgui.github.bankingapi.repository.AccountRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @PersistenceContext
    EntityManager entityManager;

    public AccountModel getAccountByUUID(UUID uuid) {
        Optional<AccountModel> resultAccount = accountRepository.findById(uuid);
        return resultAccount.orElseThrow(() -> new AccountNotFoundException(uuid));
    }

    public AccountModel saveAccount(AccountModel accountModel) {
        return accountRepository.save(accountModel);
    }

    public void subtractBalance(AccountModel accountModel, Double amount) {
        accountRepository.saveSubtractBalance(amount, accountModel.getAccountNumber());
        accountModel.setBalance(accountModel.getBalance() - amount);
    }

    public void sumBalance(AccountModel accountModel, Double amount) {
            accountRepository.saveSumBalance(amount, accountModel.getAccountNumber());
            accountModel.setBalance(accountModel.getBalance() + amount);
    }

    public Iterable<AccountModel> findAllAccounts() {
        return accountRepository.findAll();
    }

    @Transactional
    public ResponseEntity<String> deleteAccountByUUID(UUID uuid) {
        Query query = entityManager.createNativeQuery("DELETE FROM ACCOUNT_MODEL WHERE ACCOUNT_NUMBER = :uuid");
        query.setParameter("uuid", uuid);
        int deletedRows = query.executeUpdate();
        System.out.println("Rows deleted: " + deletedRows);
        return deletedRows == 1 ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
