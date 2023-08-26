package syncgui.github.bankingapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import syncgui.github.bankingapi.DTO.AccountDTO;
import syncgui.github.bankingapi.service.AccountService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping
    public List<AccountDTO> findAllClients() {
        return accountService.findAllAccounts();
    }

    @PostMapping(path = "/add")
    public @ResponseBody AccountDTO addClient(@Valid @RequestBody AccountDTO accountDTO) {
        return accountService.saveAccount(accountDTO);
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<String> removeClient(@PathVariable UUID uuid) {
        return accountService.deleteAccountByUUID(uuid);
    }
}
