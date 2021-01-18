package game1.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game1.canvas.GameCanvas;
import game1.canvas.GameFrame;


public class GameBackground {
	private Image img;
	private int x;
	private int y;
	private int w;
	private int h;
	private String url;

	
	public GameBackground() {
		this(0,0,"");
		
	}
	

	public GameBackground(int x, int y,String url) {
		this.x = x;
		this.y = y;
		this.url = url;
		
		try {
			img = ImageIO.read(new File(url));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void paint(Graphics g) {
		this.w=GameFrame.instance.getWidth();
		this.h=GameFrame.instance.getHeight();
		if(url=="res/game_background2.jpg")
			g.drawImage(img,x, y, w,h, GameCanvas.instance);
		else if (url=="res/title.png")
			g.drawImage(img,x, y, 500,300, GameCanvas.instance);

	}


}
