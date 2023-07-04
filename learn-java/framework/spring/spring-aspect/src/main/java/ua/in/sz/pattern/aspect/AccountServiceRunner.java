package ua.in.sz.pattern.aspect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.in.sz.pattern.aspect.service.AccountService;

@Slf4j
@Component
public class AccountServiceRunner implements CommandLineRunner {

	private AccountService accountService;

	@Autowired
	public AccountServiceRunner(AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	public void run(String... args) {
		accountService.execute();
	}
}
