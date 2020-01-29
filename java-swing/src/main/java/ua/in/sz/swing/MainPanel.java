package ua.in.sz.swing;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;

@Slf4j
public class MainPanel extends JPanel {
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.BLUE);
		g2d.fillRect(100, 50, 60, 80);
	}
}
