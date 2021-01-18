package game1.entity;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game1.canvas.GameCanvas;
import game1.canvas.GameFrame;

public class GameEnd {
	private static Image img;
	private int x;
	private int y;
	private int width;
	private int height;
	
	private boolean gameEnd = false;
	
	static {
		try {
			img = ImageIO.read(new File("res/game_over.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public GameEnd() {
		this.x = 600;
		this.y = 200;
		this.width = 605;
		this.height = 605;
	}
	
	
	public void paint(Graphics g) {
		if(gameEnd == true)
			g.drawImage(img, x, y, width, height, GameCanvas.instance);
	}

	public void setGameEnd(boolean gameEnd) {
		this.gameEnd = gameEnd;
	}

}
