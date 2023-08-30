package syncgui.github.bankingapi.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import syncgui.github.bankingapi.dtos.AccountDTO;
import syncgui.github.bankingapi.services.AccountService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService service;

    @GetMapping
    public List<AccountDTO> findAllClients() {
        return service.findAllAccounts();
    }

    @PostMapping(path = "/add")
    public @ResponseBody AccountDTO addClient(@Valid @RequestBody AccountDTO accountDTO) {
        return service.saveAccount(accountDTO);
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<String> removeClient(@PathVariable UUID uuid) {
        return service.deleteAccountByUUID(uuid);
    }
}
