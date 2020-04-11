package ua.in.sz.pattern.gof.visitor;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.pattern.gof.visitor.impl.*;

@Slf4j
public class Application {
	public static void main(String[] args) {
		Composite root = Composite.builder().name("root").build();
		Composite child1 = Composite.builder().name("child 1").build();

		root.add(child1);
		root.add(Leaf.builder().name("child 2").build());

		child1.add(Leaf.builder().name("child 1-1").build());
		child1.add(Leaf.builder().name("child 1-2").build());

		root.accept(new LogVisitor());
	}
}
