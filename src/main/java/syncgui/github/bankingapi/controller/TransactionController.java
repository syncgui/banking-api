package syncgui.github.bankingapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import syncgui.github.bankingapi.model.TransactionModel;
import syncgui.github.bankingapi.model.AccountModel;
import syncgui.github.bankingapi.service.TransactionService;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;


    @PostMapping(path = "/deposits/{uuid}")
    public @ResponseBody AccountModel deposit(@PathVariable UUID uuid, @Valid @RequestBody TransactionModel transactionModel) {
            return transactionService.deposit(uuid, transactionModel);
    }

    @PostMapping(path = "/withdraws/{uuid}")
    public @ResponseBody AccountModel withdraw(@PathVariable UUID uuid, @Valid @RequestBody TransactionModel transactionModel) {
        return transactionService.withdraw(uuid, transactionModel);
    }

    @PostMapping(path = "/transfers/{sourceUUID}/{destinationUUID}")
    public @ResponseBody AccountModel[] transfers(@PathVariable UUID sourceUUID, @PathVariable UUID destinationUUID, @Valid @RequestBody TransactionModel transactionModel) {
        return transactionService.transfer(sourceUUID, destinationUUID, transactionModel);
    }
}
