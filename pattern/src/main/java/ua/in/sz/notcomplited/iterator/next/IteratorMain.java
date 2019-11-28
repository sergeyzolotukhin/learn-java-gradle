package ua.in.sz.notcomplited.iterator.next;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class IteratorMain {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();

		String next = list.iterator().next();
	}
}
