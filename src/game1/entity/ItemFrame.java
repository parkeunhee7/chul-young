package game1.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game1.canvas.GameCanvas;

public class ItemFrame {
	private int x;
	private int y;
	private int width;
	private int height;
	
	private Image img;
	private Image[] itemImgs;
	public ItemFrame() {
		this(0, 0);
	}
	
	public ItemFrame(int x, int y) {
		this.x = x;
		this.y = y;
		width = 215;
		height = 215;
		itemImgs = new Image[2];
		
		try {
			img = ImageIO.read(new File("res/frame.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void paint(Graphics g) {
		int x = this.x;
		int y = this.y;
		int w = width/2;
		int h = height/2;
		for (int i = 0; i < 2; i++) {
			g.drawImage(img, x+w*i, y, x+w*(i+1), y+h, 0, 0, width, height, GameCanvas.instance);
		}
		for (int i = 0; i < itemImgs.length; i++) {
			g.drawImage(itemImgs[i], x+w*i, y, x+w*(i+1), y+h, 0, 0, width, height, GameCanvas.instance);

		}
	}

	public Image getItemImg(int index) {
		return itemImgs[index];
	}

	public void setItemImgs(Image itemImg,int index) {
		this.itemImgs[index] = itemImg;
	}
	
	
	
}
