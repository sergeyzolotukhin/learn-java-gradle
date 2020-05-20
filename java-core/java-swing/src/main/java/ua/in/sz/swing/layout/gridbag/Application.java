package ua.in.sz.swing.layout.gridbag;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;

/**
 * https://ipsoftware.ru/posts/gridbaglayout/
 */
@Slf4j
public class Application {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Layout demo");

		JPanel panel = new JPanel(new DebugGridBagLayout());

		DebugGridBagConstraints b1c = new DebugGridBagConstraints();
		b1c.gridy = 0;
		b1c.gridx = 0;
		b1c.weightx = 1;
		b1c.anchor = DebugGridBagConstraints.WEST;
		b1c.fill = DebugGridBagConstraints.BOTH;
		panel.add(new JButton("1"  ), b1c);

		DebugGridBagConstraints b3c = new DebugGridBagConstraints();
		b3c.gridy = 0;
		b3c.gridx = 1;
		b3c.weightx = 1;
		b3c.anchor = DebugGridBagConstraints.WEST;
		b3c.fill = DebugGridBagConstraints.BOTH;
		panel.add(new JButton("3"  ), b3c);

		DebugGridBagConstraints b5c = new DebugGridBagConstraints();
		b5c.gridy = 0;
		b5c.gridx = 2;
		b5c.weightx = 1;
		b5c.anchor = DebugGridBagConstraints.WEST;
		b5c.fill = DebugGridBagConstraints.HORIZONTAL;
		panel.add(new JButton("5"  ), b5c);

		DebugGridBagConstraints b2c = new DebugGridBagConstraints();
		b2c.gridy = 1;
		b2c.gridx = 1;
		b2c.gridwidth = 2;
		b2c.weightx = 1;
		b2c.anchor = DebugGridBagConstraints.EAST;
		b2c.fill = DebugGridBagConstraints.HORIZONTAL;
		panel.add(new JButton("2"  ), b2c);

		frame.setContentPane(panel);

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
