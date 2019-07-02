package com.kitri.sguo.view.lobby;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class LobbyUserImageP extends JPanel{
	
	private BufferedImage bi;

	public LobbyUserImageP(BufferedImage bi) {
		this.bi = bi;
	}
	
	public void setLobbyImage(String filepath) {
		try {
			bi = ImageIO.read(new File(filepath));
			bi = (BufferedImage) bi.getScaledInstance(bi.getWidth(null), bi.getHeight(null), Image.SCALE_SMOOTH);
			invalidate();
			repaint();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(bi, 0, 0, this);
		g2d.dispose();
	}
}
