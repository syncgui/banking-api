package syncgui.github.bankingapi.mappers;

import syncgui.github.bankingapi.dtos.TransactionDTO;
import syncgui.github.bankingapi.models.TransactionModel;

public class TransactionMapper {

    public static TransactionDTO toDTO(TransactionModel transactionModel) {
        return TransactionDTO.builder().amount(transactionModel.getAmount())
                .destinationAccount(transactionModel.getDestinationAccount()).build();
    }

    public static TransactionModel toModel(TransactionDTO transactionDTO) {
        return TransactionModel.builder().amount(transactionDTO.getAmount())
                .destinationAccount(transactionDTO.getDestinationAccount()).build();
    }
}
