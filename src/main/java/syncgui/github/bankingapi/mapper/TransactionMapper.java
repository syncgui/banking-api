package syncgui.github.bankingapi.mapper;

import syncgui.github.bankingapi.DTO.TransactionDTO;
import syncgui.github.bankingapi.model.TransactionModel;

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
