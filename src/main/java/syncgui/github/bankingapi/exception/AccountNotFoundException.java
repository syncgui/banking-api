package syncgui.github.bankingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;
import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends NoSuchElementException {

    public AccountNotFoundException(UUID uuid) {
        super("Account with this ID may not exist!");
    }

}
