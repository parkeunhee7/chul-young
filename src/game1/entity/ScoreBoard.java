package game1.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game1.canvas.GameCanvas;

public class ScoreBoard {
	private int x;
	private int y;
	private double width;
	private double height;
	
	private Image img;
	private String imgSrc;

	private UnitScore[] unitScores;
	private UnitScore boyScore;
	private UnitScore boyScoreScale;
	private UnitScore girlScore;
	private UnitScore girlScoreScale;
	
	private ItemFrame boyItemFrame;
	private ItemFrame girlItemFrame;
	
	private Image[] boards;
	
	public ScoreBoard() throws IOException {
		this(0,0,null);
	}
	
	public ScoreBoard(int x, int y, String imgSrc) throws IOException {
		this.x = x;
		this.y = y;
		
		boards = new Image[2];
		for(int i=0; i<boards.length; i++)
			boards[i] = ImageIO.read(new File("res/score_board"+(i+1)+".png")); 
		
		unitScores = new UnitScore[4];
		boyScore = new UnitScore(220, 370);
		girlScore = new UnitScore(1710, 370);
		boyScoreScale = new UnitScore(150, 370);
		girlScoreScale = new UnitScore(1640, 370);
		unitScores[0] = boyScore;
		unitScores[1] = girlScore;
		unitScores[2] = boyScoreScale;
		unitScores[3] = girlScoreScale;
		
		boyItemFrame = new ItemFrame(50,550);
		girlItemFrame = new ItemFrame(1650,550);
	}

	
	
	public ItemFrame getBoyItemFrame() {
		return boyItemFrame;
	}

	public ItemFrame getGirlItemFrame() {
		return girlItemFrame;
	}

	public UnitScore[] getUnitScores() {
		return unitScores;
	}
	public void update() {
		for(int i=0; i<unitScores.length; i++)
			unitScores[i].update();
	}
	
	public void paint(Graphics g) {
		g.drawImage(boards[0], 20, 300, 300, 200, GameCanvas.instance);
		g.drawImage(boards[1], 1600, 300, 300, 200, GameCanvas.instance);
		
		for(int i=0; i<unitScores.length; i++)
			unitScores[i].paint(g);
		
		boyItemFrame.paint(g);
		girlItemFrame.paint(g);
	}

}
