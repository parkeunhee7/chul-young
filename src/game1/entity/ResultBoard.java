package game1.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game1.canvas.ResultCanvas;

public class ResultBoard {
	private int x;
	private int y;
	private final int width;
	private final int height;
	private Image resultImg;
	
	
	public ResultBoard() {
		this(0, 0);
	}
	
	public ResultBoard(int x, int y) {
		this.x = x;
		this.y = y;
		
		width = 1000;
		height = 1000;
		
		try {
			resultImg = ImageIO.read(new File("res/result_board.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public void paint(Graphics g) {
		
		g.drawImage(resultImg, 0, 0, ResultCanvas.instance);
		
	}
	
	
	
	
}
