package ua.in.sz.guice;

import com.google.inject.AbstractModule;
import ua.in.sz.guice.service.EmailMessageService;
import ua.in.sz.guice.service.MessageService;

public class AppModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(MessageService.class).to(EmailMessageService.class);
	}

//	@Provides
//	static MessageService messageService() {
//		return new EmailService();
//	}
}
