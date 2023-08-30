package syncgui.github.bankingapi.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import syncgui.github.bankingapi.dtos.AccountDTO;
import syncgui.github.bankingapi.dtos.TransactionDTO;
import syncgui.github.bankingapi.services.TransactionService;

import java.util.UUID;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService service;


    @PostMapping(path = "/deposits/{uuid}")
    public @ResponseBody AccountDTO deposit(@PathVariable UUID uuid, @Valid @RequestBody TransactionDTO transactionDTO) {
            return service.deposit(uuid, transactionDTO);
    }

    @PostMapping(path = "/withdraws/{uuid}")
    public @ResponseBody AccountDTO withdraw(@PathVariable UUID uuid, @Valid @RequestBody TransactionDTO transactionDTO) {
        return service.withdraw(uuid, transactionDTO);
    }

    @PostMapping(path = "/transfers/{sourceUUID}")
    public @ResponseBody AccountDTO transfer(@PathVariable UUID sourceUUID, @Valid @RequestBody TransactionDTO transactionDTO) {
        return service.transfer(sourceUUID, transactionDTO);
    }
}
