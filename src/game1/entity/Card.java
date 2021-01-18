package game1.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import game1.canvas.GameCanvas;

public class Card {
	private final static int width = 300;
	private final static int height = 400;
	private final static int Gamewidth = 300/3;
	private final static int Gameheight = 400/3;
	private final static int START = 1;
	private final static int GIRL = 2;
	private final static int BOY = 3;
	
	private static Image cardBack;
	private static Image cardFront;
	private Image img;
	private int index;
	private int x;
	private int y;
	private int number;
	private boolean hide;
	
	private boolean isSelected;
	private int backType;
	static {
		try {
			cardFront = ImageIO.read(new File("res/card_number.png"));
			cardBack = ImageIO.read(new File("res/card_back.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Card() {
		this(0, 0,1);
	}
	public Card(int x, int y,int number) { //1~60
		this.x = x;
		this.y = y;
		this.number = number;
		
		
		this.index = number-1;
		img = cardFront;
		hide = false;
	}
	

	
	
	public void paint(Graphics g) {
		int dx1 = x;
		int dy1 = y;
		int dx2 = x+Gamewidth;
		int dy2 = y+Gameheight;
		int sx1 = width*index;
		int sy1 = 0;
		int sx2 = width*(index+1);
		int sy2 = height;
		
		if(hide) {
			switch (backType) {
			case START:
				index = 0;
				break;
			case GIRL:
				index = 1;
				break;
			case BOY:
				index = 2;
				break;
			default :
				index = 2;
				break;
			}
			img = cardBack;
		}
		else {
			index=number-1;
			img = cardFront;
		}
		g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, GameCanvas.instance);
	}
	
	public boolean isSelected(double x, double y) {  
		if(this.x<x&&x<this.x+Gamewidth     
				&&this.y<y&&y<this.y+Gameheight) {
			isSelected=true;
			return isSelected;
		}
		isSelected= false;
		return isSelected;
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	public Image getImg() {
		return img;
	}
	public void setHide(boolean hide) {
		this.hide = hide;
	}
	public boolean isHide() {
		return hide;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getBackType() {
		return backType;
	}
	public void setBackType(int backType) {
		this.backType = backType;
	}

	
	
}
