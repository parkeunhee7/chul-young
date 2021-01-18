package game1.entity;

import java.awt.Button;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;

import org.w3c.dom.events.MouseEvent;

import game1.canvas.GameFrame;
import game1.canvas.IntroCanvas;


public abstract class ButtonItem {
	private int width;
	private int height;
	private int x;
	private int y;
	private Image img;
	private Image changeImg;
	private Image basicImg;
	private boolean isSelected;
	

	public ButtonItem() {
		this(100, 100,100,100);
	}
	
	public ButtonItem(int x, int y,int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		img = getImage();
		changeImg = getChangeImage();
		basicImg = getBasicImage();
		isSelected = false;
	}

protected abstract Image getBasicImage();

// 마우스가 올려졌을 때 이미지 바껴야한다.

protected abstract Image getImage();
	// 클랙됐을떄 명령 수행.
//	protected abstract void mouseClick(int x, int y);
	
protected abstract Image getChangeImage();
		
	
public boolean isSelected(int x, int y) {  
	if(this.x<x&&x<this.x+width     
			&&this.y<y&&y<this.y+height) {
		isSelected=true;
		return isSelected;
	}
	isSelected= false;
	return isSelected;
}	

public void paint(Graphics g) {
		g.drawImage(img, x, y, width, height,IntroCanvas.instance);
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	
	
	public void setChangeImg(Image changeImg) {
		this.changeImg = changeImg;
	}

	public void setBasicImg(Image basicImg) {
		this.basicImg = basicImg;
	}

	public Image getChangeImg() {
		return changeImg;
	}

	public Image getBasicImg() {
		return basicImg;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean getSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	



	
	
	
}