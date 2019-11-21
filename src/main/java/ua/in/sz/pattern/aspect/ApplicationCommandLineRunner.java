package ua.in.sz.pattern.aspect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationCommandLineRunner implements CommandLineRunner {

	private AccountService accountService;

	@Autowired
	public ApplicationCommandLineRunner(AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	public void run(String... args) {
		accountService.execute();
	}
}
