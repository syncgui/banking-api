package syncgui.github.bankingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import syncgui.github.bankingapi.model.AccountModel;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, UUID> {

    @Query(value = "UPDATE ACCOUNT_MODEL SET BALANCE = BALANCE - ?1 WHERE ACCOUNT_NUMBER = ?2", nativeQuery = true)
    @Transactional
    @Modifying
    void saveSubtractBalance(Double amount, UUID uuid);

    @Query(value = "UPDATE ACCOUNT_MODEL SET BALANCE = BALANCE + ?1 WHERE ACCOUNT_NUMBER = ?2", nativeQuery = true)
    @Transactional
    @Modifying
    void saveSumBalance(Double amount, UUID uuid);

    @Query(value = "SELECT COUNT(ACCOUNT_NUMBER) FROM ACCOUNT_MODEL WHERE ACCOUNT_NUMBER = ?1", nativeQuery = true)
    int checkIfAccountExists(UUID uuid);
}
