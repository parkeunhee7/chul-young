package game1.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game1.canvas.IntroCanvas;


public class IntroBackground {
	private static Image img;
	private int x;
	private int y;
	private int w;
	private int h;

	static {
		try {
			img = ImageIO.read(new File("res/game_background.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public IntroBackground() {
		this(0,0);
		
	}
	

	public IntroBackground(int x, int y) {
		this.x = x;
		this.y = y;
		
		
	}

	public void paint(Graphics g) {
		this.w=IntroCanvas.instance.getWidth();
		this.h=IntroCanvas.instance.getHeight();
		g.drawImage(img,x, y, w,h, IntroCanvas.instance);
	}


}
