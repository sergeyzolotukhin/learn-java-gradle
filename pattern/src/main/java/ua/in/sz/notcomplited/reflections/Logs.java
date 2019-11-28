package ua.in.sz.notcomplited.reflections;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Slf4j
@UtilityClass
@SuppressWarnings("WeakerAccess")
public class Logs {

	public static void classes(Class<?> clazz) {
		log.info("clazz: {}", clazz);

		Logs.fields(clazz);
		Logs.constructors(clazz);
		Logs.methods(clazz);
	}

	public static void constructors(Class<?> clazz) {
		for (Constructor<?> method : clazz.getDeclaredConstructors()) {
			log.info("constructor: [{}] s={}", method.getName(), method.isSynthetic());
		}
	}

	public static void fields(Class<?> clazz) {
		for (Field field : clazz.getDeclaredFields()) {
			log.info("field: [{}] s={}", field.getName(), field.isSynthetic());
		}
	}

	public static void methods(Class<?> clazz) {
		for (Method method : clazz.getDeclaredMethods()) {
			log.info("declare method: [{}] s={}", method.getName(), method.isSynthetic());
		}

		/*
		for (Method method : clazz.getMethods()) {
			log.info("method: [{}] s={}, b={}", method.getName(), method.isSynthetic(), method.isBridge());
		}
		 */
	}

}
