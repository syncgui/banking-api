package syncgui.github.bankingapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import syncgui.github.bankingapi.model.AccountModel;
import syncgui.github.bankingapi.service.AccountService;

import java.util.UUID;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping
    public Iterable<AccountModel> findAllClients() {
        return accountService.findAllAccounts();
    }

    @PostMapping(path = "/add")
    public @ResponseBody AccountModel addClient(@Valid @RequestBody AccountModel accountModel) {
        return accountService.saveAccount(accountModel);
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<String> removeClient(@PathVariable UUID uuid) {
        return accountService.deleteAccountByUUID(uuid);
    }
}
