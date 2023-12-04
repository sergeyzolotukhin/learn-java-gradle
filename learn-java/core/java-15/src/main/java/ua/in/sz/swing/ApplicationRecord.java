package ua.in.sz.swing;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApplicationRecord {

	public static void main(String[] args) {
		Person person = new Person("Serhij", -40);
		log.info("Person: {}", person);
	}

	public record Person(String name, int age) {
		public Person {
			if(age < 0) {
				throw new IllegalArgumentException("Age cannot be negative");
			}
		}
	}
}
