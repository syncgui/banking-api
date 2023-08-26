package syncgui.github.bankingapi.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import syncgui.github.bankingapi.model.AccountModel;

import java.util.UUID;

@Repository
public interface AccountRepository extends CrudRepository<AccountModel, UUID> {

    @Query(value = "UPDATE ACCOUNT_MODEL SET BALANCE = BALANCE - ?1 WHERE ACCOUNT_NUMBER = ?2", nativeQuery = true)
    @Transactional
    @Modifying
    void saveSubtractBalance(Double amount, UUID uuid);

    @Query(value = "UPDATE ACCOUNT_MODEL SET BALANCE = BALANCE + ?1 WHERE ACCOUNT_NUMBER = ?2", nativeQuery = true)
    @Transactional
    @Modifying
    void saveSumBalance(Double amount, UUID uuid);
}
