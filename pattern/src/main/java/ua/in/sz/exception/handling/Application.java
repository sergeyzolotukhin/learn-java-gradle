package ua.in.sz.exception.handling;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.list.TransformedList;

import java.util.List;

@Slf4j
public class Application {
	public static final List<String> list = TransformedList.transformedList(Lists.newArrayList(),
			new Transformer<String, String>() {
				@Override
				public String transform(String input) {
					return String.format("text %s", input);
				}
			});

	public static void main(String[] args) {
		try {
			method_1();
		} catch (UnsupportedOperationException e) {
			log.error("Exception handling", e);
		}

	}

	// Don't clear the stack trace when re-throwing an exception

	private static void method_1() {
		try {
			method_2();
		} catch (UnsupportedOperationException e) {
			log.info("Exception in method");
			throw e;
		}

	}

	private static void method_2() {
		list.get(2);
	}

}
