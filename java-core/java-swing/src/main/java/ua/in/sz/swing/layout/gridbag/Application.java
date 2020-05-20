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
		b1c.fill = GridBagConstraints.BOTH;
		panel.add(new JButton("1"  ), b1c);

		DebugGridBagConstraints b3c = new DebugGridBagConstraints();
		b3c.gridy = 0;
		b3c.gridx = 3;
		b3c.fill = GridBagConstraints.BOTH;
		panel.add(new JButton("3"  ), b3c);

		DebugGridBagConstraints b2c = new DebugGridBagConstraints();
		b2c.gridy = 1;
		b2c.gridx = 1;
		b2c.gridwidth = 2;
		b2c.gridheight = 2;
		b2c.fill = GridBagConstraints.BOTH;
		panel.add(new JButton("2"  ), b2c);

		frame.setContentPane(panel);

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
