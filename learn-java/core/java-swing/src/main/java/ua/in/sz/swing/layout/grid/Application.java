package ua.in.sz.swing.layout.grid;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;

@Slf4j
public class Application {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Layout demo");

		JPanel panel = new JPanel(new GridLayout(3, 3));
		panel.add(new JButton("1"  ));
		panel.add(new JButton("2"));
		panel.add(new JButton("3"));

		panel.add(new JButton("4"));
		panel.add(new JButton("5"));
		panel.add(new JButton("6"));

		panel.add(new JButton("7"));

		frame.setContentPane(panel);

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
