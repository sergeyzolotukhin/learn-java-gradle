package ua.in.sz.spring.transaction.readonly;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.in.sz.spring.transaction.readonly.service.TxReadOnlyService;

@Slf4j
@Getter
@Component
@RequiredArgsConstructor
public class TxReadOnlyApplicationCommandLineRunner implements CommandLineRunner {

	private final TxReadOnlyService txReadOnlyService;

	@Override
	@SneakyThrows
	public void run(String...args) {
		log.info("Start application");

		txReadOnlyService.load();

		log.info("End application");
	}
}