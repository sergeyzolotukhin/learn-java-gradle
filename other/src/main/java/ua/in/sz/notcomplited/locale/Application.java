package ua.in.sz.notcomplited.locale;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

@Slf4j
public class Application {
	public static void main(String[] args) {
//		for (String locale : Arrays.asList("EN", "RU")) {
//			Locale.setDefault(new Locale("en", "EN"));

			ResourceBundle test = ResourceBundle.getBundle("locale");
			System.out.println(test.getString("message"));
//		}
	}
}
