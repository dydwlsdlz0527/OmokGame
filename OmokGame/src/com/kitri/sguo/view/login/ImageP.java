package com.kitri.sguo.view.login;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageP extends JPanel {

	private BufferedImage bi;
	private final int newWidth = 164;
	private final int newHeight = 257;
	private int imageWidth = 0;
	private int imageHeight = 0;
	private BufferedImage newImage;
	int w;
	int h;
	double ratio;
	private Image resizeIamge;
	private String mainPosition = "";
	
	public ImageP() {};

	public void setImage(String filepath) {

		try {
			// ���� �̹��� ��������
			bi = ImageIO.read(new File(filepath));

			imageWidth = bi.getWidth(null);
			imageHeight = bi.getHeight(null);

			if (mainPosition.equals("W")) { // ���̱���
				ratio = (double) newWidth / (double) imageWidth;
				w = (int) (imageWidth * ratio);
				h = (int) (imageHeight * ratio);
			} else if (mainPosition.equals("H")) { // ���̱���
				ratio = (double) newHeight / (double) imageHeight;
				w = (int) (imageWidth * ratio);
				h = (int) (imageHeight * ratio);
			} else { // ������ (��������)
				w = newWidth;
				h = newHeight;
			}
			resizeIamge = bi.getScaledInstance(w, h, Image.SCALE_SMOOTH);
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
		g.drawImage(resizeIamge, 0, 0, this);
		g.dispose();
	}


}
