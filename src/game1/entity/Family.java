package game1.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import game1.canvas.IntroCanvas;

public class Family {

	private double x;
	private double y;
	private double vx;
	private double vy;
	private double dx;
	private double dy;
	private double width;
	private double height;
	private Image img;

	private int walkTempo = 5;
	private int moveIndex = 0;;
	private int timeoutForMoving = 120;
	private int speed = 5;


	private Random rand;

	public Family() {
		this(0,0,"res/family_1.png");

	}

	public Family(double x, double y, String url) {

		this.x = x;
		this.y = y;
		width = 212;
		height = 212;


		try {
			img = ImageIO.read(new File(url));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		rand = new Random();
	}

	public void update() {


		int x = (int)this.x;
		int y = (int)this.y;
		int vx = (int)this.vx;
		int vy = (int)this.vy;
		int dx = (int)this.dx;
		int dy = (int)this.dy;

		timeoutForMoving--;
		
		if(timeoutForMoving == 0) {
			int width = (int)this.width;
			int height = (int)this.height;

			int w = IntroCanvas.instance.getWidth()-(int)width;  //this.width;
			int h = IntroCanvas.instance.getHeight()-(int)height; //this.height;
			dx = rand.nextInt(w)+(int)width /2; //this.width/2;
			dy = rand.nextInt(h)+(int)height/2 ;   //this.height/2;

			System.out.printf("dx : %d\n",dx);
			System.out.printf("dy : %d\n",dy);
			
			this.move(dx,dy);
			timeoutForMoving = rand.nextInt(60)+60;//60~119
			
		}
		
		if((dx - 1 <= x && x <= dx + 1) && 
				(dy - 1 <= y && y <= dy + 1)) {
			vx = 0;
			vy = 0;
			timeoutForMoving = 1; //10이면 정지함 10,9 반복
			
		}
		else 
			this.x += vx;
			this.y += vy;
		
	}

	private void move(int dx, int dy) {
		

		this.dx = dx;
		this.dy = dy;
		double w = this.dx - x;
		double h = this.dy - y;
		double d = Math.sqrt(w*w + h*h);
		this.vx = w/d*speed;
		this.vy = h/d*speed;

		this.x += vx;
		this.y += vy;

	}

	public void paint(Graphics g) {


		int dx1 = (int)this.x;
		int dy1 = (int)this.y;
		int dx2 = dx1+ (int)this.width-150;
		int dy2 = dy1+ (int)this.height-150;
		int offsetX = 0;
		int w =(int)this.width;

		if(walkTempo == 0) {
			moveIndex++;			
			moveIndex = moveIndex % 24;   //시작이 0 이 되야한다.  

			walkTempo = 3;
		}
		else {
			walkTempo--;
		}

		offsetX = moveIndex * w;

		g.drawImage(img, dx1, dy1, dx2, dy2,
				offsetX, 0, w+offsetX, 212, IntroCanvas.instance);

	}


}