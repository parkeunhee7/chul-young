package game1.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game1.canvas.GameCanvas;

public class Item {
	
	private static final int SPEED = 1; //아이템 타입
	private static final int STOP = 2;
	private static final int HIDE = 3;
	private static final int WIND = 4;
	private static final int REVERSE = 5;
	
	
	private final int betWidth = 120;
	private final int betHeight = 150;
	private final int cardRow = 10;
	private final int i;
	private final int cardCol;
	private int x;
	private int y;
	private int width = 200;
	private int height = 200;
	
	private Image img;
	private int typeCnt; 
	private int type; //아이템 타입
	private int yIndex;
	private int timeTempo =8; //아이템 효과 속도 조절
	private boolean isSelected;
	
	public Item() throws IOException {
		i = (int)(Math.random()*50);
		cardCol = (int)(Math.random()*5);
		typeCnt = 5;
		type = (int)(Math.random()*typeCnt)+1;  //type = 1~3;
		this.x = (i%cardRow)*betWidth+350; // i = 0~29;
		this.y = cardCol*betHeight+100;  // cardCol = 0~4;
		
		switch (type) {
		case SPEED:
			img = ImageIO.read(new File("res/shoes-cutout.png"));
			break;

		case STOP:
			img = ImageIO.read(new File("res/stop.png"));
			break;
		case HIDE:
			img = ImageIO.read(new File("res/hide.png"));
			break;
		case WIND:
			img = ImageIO.read(new File("res/wind.png"));
			break;
		case REVERSE:
			img = ImageIO.read(new File("res/reverse.png"));
			break;
		}
	}

	public boolean isSelected(double x, double y) {  
		if(this.x+10<x&&x<this.x+width/3+10     
				&&this.y+10<y&&y<this.y+height/3+10) {
			isSelected=true;
			return isSelected;
		}
		isSelected= false;
		return isSelected;
	}
	
	
	public void update() {
		if(timeTempo ==0) {
		if(yIndex==10)
			yIndex=0;
		yIndex++;
		timeTempo =8;
		}
		else
			timeTempo--;
		
	}
	
	public void paint(Graphics g) {
		
		int dx1 = x+20;
		int dy1 = y+yIndex+5;
		int dx2 = x+width/3+20;
		int dy2 = y+height/3+yIndex+5;
		int sx1 = 0;
		int sy1 = 0;
		int sx2 = width;
		int sy2 = height;
		g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, GameCanvas.instance);
	}

	public int getType() {
		return type;
	}

	public Image getImg() {
		return img;
	}

	
	
	
}
