package syncgui.github.bankingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import syncgui.github.bankingapi.model.TransactionModel;

import java.util.UUID;
@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, UUID> {
}
