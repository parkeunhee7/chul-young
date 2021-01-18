package game1.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game1.canvas.GameCanvas;

public class UnitScore {
	private int x;
	private int y;
	private int width;
	private int height;
	
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private int offsetX;
	
	private int scoreIndex;
	
	private boolean scale;
	
	private Image img;
	private String imgSrc;
	
	public UnitScore() {
		this(0,0);
	}
	
	public UnitScore(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 30;
		this.height = 39;
		imgSrc = "res/number.png";
		
		try {
			img = ImageIO.read(new File(imgSrc));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.scale = false;
	}
	
	public void update() {
		scoreIndex = scoreIndex%10;
	}
	
	public void paint(Graphics g) {
		int w = this.width;
		int h = this.height;
		int x1 = (int)this.x ;
		int y1 = (int)this.y ;
		int x2 = x1+2*w;
		int y2 = y1+2*h;

		int offsetX = scoreIndex * w;
		if(scoreIndex+1 > 9) {
			scale = true;			
		}
		else
			scale = false;
		g.drawImage(img, x1, y1, x2, y2, 
				0+offsetX, 0, 0+w+offsetX, h, GameCanvas.instance);
	}

	public void setScoreIndex(int scoreIndex) {
		this.scoreIndex = scoreIndex;
	}

	public boolean isScale() {
		return scale;
	}


}
