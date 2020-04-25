package ua.in.sz.notcomplited.commonschain;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.Filter;
import org.apache.commons.chain.impl.CatalogBase;
import org.apache.commons.chain.impl.ChainBase;
import org.apache.commons.chain.impl.ContextBase;

@Slf4j
public class Application {
	public static void main(String[] args) throws Exception {
		Context context = new ContextBase();

		CatalogBase catalog = new CatalogBase();
		catalog.addCommand("my", new MyChain());

		Command command = catalog.getCommand("my");

		log.info("Start execution");

		command.execute(context);

		log.info("End execution");
	}

	// ================================================================================================================
	// chains/catalogs
	// ================================================================================================================

	private static class MyChain extends ChainBase {
		public MyChain() {
			super();
			addCommand(new ExceptionFilter());

			addCommand(new StartCmd());
			addCommand(new DoCmd());
			addCommand(new EndCmd());
			addCommand(new ExceptionCmd());
		}
	}

	// ================================================================================================================
	// commands
	// ================================================================================================================

	private static class StartCmd implements Command {
		@Override
		public boolean execute(Context context) {
			log.info("Start");
			return false;
		}
	}

	private static class DoCmd implements Command {
		@Override
		public boolean execute(Context context) {
			log.info("Do");
			return false;
		}
	}

	private static class EndCmd implements Command {
		@Override
		public boolean execute(Context context) {
			log.info("End");
			return false;
		}
	}

	private static class ExceptionCmd implements Command {
		@Override
		public boolean execute(Context context) {
			log.info("Throw");
			throw new RuntimeException("Exception message");
		}
	}

	private static class ExceptionFilter implements Filter {
		@Override
		public boolean postprocess(Context context, Exception exception) {
			log.warn("Exception skipped: {}", exception.getMessage());
			return true;
		}

		@Override
		public boolean execute(Context context) throws Exception {
			return false;
		}
	}
}
