package game1.canvas;

import java.awt.Canvas;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game1.entity.ButtonItem;
import game1.entity.Family;
import game1.entity.GameBoard;
import game1.entity.IntroBackground;
import game1.entity.MainButton;
import game1.entity.ResultBoard;
import game1.entity.Unit;

public class ResultCanvas extends Canvas {
	public static Canvas instance;
	private int boyResult;
	private int girlResult;
	private MainButton retryButton;
	private MainButton homeButton;
	private String url;
	private Image img;
	private Image backgroundImg;
	private boolean win;
	private boolean draw;
	public ResultCanvas() {
		this(0, 0);
	}

	public ResultCanvas(int boyResult, int girlResult) {
		instance=this;
		this.boyResult = boyResult;
		this.girlResult = girlResult;
		System.out.println(this.boyResult);
		System.out.println(this.girlResult);
		retryButton = new MainButton(300, 300, 250, 250, 3);
		homeButton = new MainButton(1300, 300, 250, 250, 4);
		
		if(boyResult>girlResult) { //boy가 이기면
			url="res/winner_boy.png";
			win=true;
		}
		else if(boyResult<girlResult) {//girl이 이기면
			url="res/winner_girl.png";
			win=true;
		}
		else {
			url="res/winner_draw.png";
			draw = true;
		}
		try {
			img = ImageIO.read(new File(url));
			//backgroundImg = ImageIO.read(new File("res/result_background.png"));
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				
					try {
						if(retryButton.isSelected(x, y))
						GameFrame.instance.switchCanvas(ResultCanvas.this, GameCanvas.class);
						if(homeButton.isSelected(x, y))
							GameFrame.instance.switchCanvas(ResultCanvas.this, IntroCanvas.class);
					} catch (InstantiationException | IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
					
					if(homeButton.isSelected(x, y)) {
						homeButton.setImg(homeButton.getChangeImg());
					}	
					else
						homeButton.setImg(homeButton.getBasicImg());
					
					if(retryButton.isSelected(x, y)) {
						retryButton.setImg(retryButton.getChangeImg());
					}	
					else
						retryButton.setImg(retryButton.getBasicImg());

					
					//System.out.println(btnItem[i].isSelected(x, y));
			}
		
		});
		
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {

					repaint();
					//System.out.println("repaint");

					try {
						Thread.sleep(17); //(1000/60) 60fps 
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}


	@Override
	public void update(Graphics g) {
		paint(g);
	}
	@Override
	public void paint(Graphics g) {
		// 그림을 그릴 수 있는 곳은 window(=canvas)와 image다.
		Image buf = this.createImage(getWidth(), getHeight());
		Graphics bg = buf.getGraphics(); //그림을 그릴 수 있는 도구
		
		int width = GameFrame.getInstance().getWidth();
		int height = GameFrame.getInstance().getHeight();
		
		bg.drawImage(backgroundImg, 0, 0, width, height, 0, 0, 507, 511, ResultCanvas.instance);
		if(win)
		bg.drawImage(img, 550, 0, 550+704, 0+900, 0, 0, 704, 1116, ResultCanvas.instance);
		else if(draw)
			bg.drawImage(img, 550, 0, 550+704, 0+900, 0, 0, 704, 1184, ResultCanvas.instance);
		
		retryButton.paint(bg);
		homeButton.paint(bg);
		g.drawImage(buf, 0, 0, this);

	}


	public void setBoyResult(int boyResult) {
		this.boyResult = boyResult;
	}


	public void setGirlResult(int girlResult) {
		this.girlResult = girlResult;
	}

}
