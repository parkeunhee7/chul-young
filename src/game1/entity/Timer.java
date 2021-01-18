package game1.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game1.canvas.GameCanvas;


public class Timer {

	private double x;
	private double y;
	private double width;
	private double height;
	private Image img;
	private Image cntImg;

	private int timeIndex = 0;
	private int timeTempo = 5;
	private boolean start;
	private int time;
	private TimerListener listener;
	private int tIndex;
	private int settingTime;
	private int oneTime;
	public Timer() {
		this(100, 100);
	}

	public Timer(int x, int y) {	

		try {
			img = ImageIO.read(new File("res/timer.png"));
			cntImg = ImageIO.read(new File("res/countdown.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.x = x;
		this.y = y;
		this.width = 624;
		this.height = 200;
		time = 300; // 3초 뒤에 시작;

		tIndex = 0;
		settingTime=time;
	}



	public void setListener(TimerListener listener) {
		this.listener = listener;
	}

	public void update() {
		if((time==settingTime*(3-tIndex)/3)) { //세팅 시간의 3/3 2/3 1/3 이 될때마다 321 프린트
			tIndex++;
		}
		if(time ==0) {
			listener.unitMovable();
			start = true;
		}
		else if(time>0) {
			time --;
		}
		if(start) { //게임시간 조절 부분
			if(timeTempo == 0 && timeIndex!=59) { // 0초되면 시간 멈춤
				timeIndex++;
				timeIndex = timeIndex % 60; // 

				timeTempo = 160; // 80이 거의 1초 =
			}
			else	
				timeTempo--;
		}

		if(timeIndex==59) {
			start = false;
			if( timeTempo == 0 )
				listener.gameOver(); // 게임 종료
		}
		
		if(start&&(timeIndex%4==0)&&timeTempo==130) {// 시간에 따라 아이템 생성 
			listener.itemCreate();
			listener.itemCreate();

		}
		
	}
	public void paint(Graphics g){

		int w = (int)this.width;
		int h = (int)this.height;
		int x1 = (int)this.x+200;
		int y1 = (int)this.y;
		int x2 = x1 + w+400;
		int y2 = y1 + h; 	  	  	  

		int offsetY = timeIndex * h;

		g.drawImage(img, x1, y1, x2, y2 ,
				0, offsetY, w, h + offsetY, GameCanvas.instance);

		w = 59;
		h = 80;
		int dx1 = 800;
		int dy1 = 300;
		int dx2 = 900;
		int dy2 = 500;
		int sx1 = (3-tIndex)*w;
		int sy1 = 0;
		int sx2 = (4-tIndex)*w;
		int sy2 = 80;
		g.drawImage(cntImg, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, GameCanvas.instance);
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}


}
