package ua.in.sz.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import jakarta.inject.Named;
import ua.in.sz.guice.service.EmailMessageService;
import ua.in.sz.guice.service.MessageService;
import ua.in.sz.guice.service.NoopMessageService;

public class AppModule extends AbstractModule {
	@Override
	protected void configure() {
//		bind(MessageService.class).to(EmailMessageService.class);
//		bind(MessageService.class).to(NoopMessageService.class);
	}

	@Provides
	@Named("email")
	MessageService emailMessageService() {
		return new EmailMessageService();
	}

	@Provides
	@Named("noop")
	MessageService noopMessageService() {
		return new NoopMessageService();
	}
}
