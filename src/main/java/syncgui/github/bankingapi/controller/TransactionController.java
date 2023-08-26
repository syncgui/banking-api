package syncgui.github.bankingapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import syncgui.github.bankingapi.DTO.AccountDTO;
import syncgui.github.bankingapi.DTO.TransactionDTO;
import syncgui.github.bankingapi.service.TransactionService;

import java.util.UUID;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;


    @PostMapping(path = "/deposits/{uuid}")
    public @ResponseBody AccountDTO deposit(@PathVariable UUID uuid, @Valid @RequestBody TransactionDTO transactionDTO) {
            return transactionService.deposit(uuid, transactionDTO);
    }

    @PostMapping(path = "/withdraws/{uuid}")
    public @ResponseBody AccountDTO withdraw(@PathVariable UUID uuid, @Valid @RequestBody TransactionDTO transactionDTO) {
        return transactionService.withdraw(uuid, transactionDTO);
    }

    @PostMapping(path = "/transfers/{sourceUUID}")
    public @ResponseBody AccountDTO transfer(@PathVariable UUID sourceUUID, @Valid @RequestBody TransactionDTO transactionDTO) {
        return transactionService.transfer(sourceUUID, transactionDTO);
    }
}
