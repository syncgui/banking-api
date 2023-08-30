package syncgui.github.bankingapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import syncgui.github.bankingapi.models.TransactionModel;

import java.util.UUID;
@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, UUID> {
}
