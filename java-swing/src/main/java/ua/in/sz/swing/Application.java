package ua.in.sz.predicate;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

@Slf4j
public class Application {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Canvas demo");
		frame.add(new MainPanel());
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
