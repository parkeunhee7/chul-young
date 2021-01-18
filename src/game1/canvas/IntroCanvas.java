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
import game1.entity.GameBackground;
import game1.entity.IntroBackground;
import game1.entity.MainButton;

public class IntroCanvas extends Canvas {
	public static Canvas instance;
	private ButtonItem[] btnItem;
	private MainButton playBtn;
	private IntroBackground background;
	private GameBackground title;
	
	public IntroCanvas() {
		instance=this;
		playBtn = new MainButton(150, 500,400,400,1);
		background = new IntroBackground();
		title = new GameBackground(600, 150, "res/title.png");
		
		
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				GameFrame.getInstance().effect("Button");
				//if(playBtn.contains(x, y)) {
					try {
						if(playBtn.isSelected(x, y)) {
							
						GameFrame.instance.switchCanvas(IntroCanvas.this, InfoCanvas.class);
						}//if(exitBtn.getSelected())
						//	System.exit(0); // 종료
					} catch (InstantiationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					//}
				}
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
					
					if(playBtn.isSelected(x, y)) {
						playBtn.setImg(playBtn.getChangeImg());
					}	
					else
						playBtn.setImg(playBtn.getBasicImg());

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

	//		@Override
	//		public boolean mouseMove(Event evt, int x, int y) { // 마우스 올리면 색깔 변함.
	//			for (int i = 0; i < btnItem.length; i++) {
	//				if(x>btnItem[i].getX()&&x<(btnItem[i].getX()+btnItem[i].getWidth())&&y>btnItem[i].getY()&&y<(btnItem[i].getY()+btnItem[i].getHeight())) {
	//					btnItem[i].setImg(btnItem[i].getChangeImg());
	//				}	
	//				else
	//					btnItem[i].setImg(btnItem[i].getBasicImg());
	//	
	//			}
	//			return true;
	//		}


	@Override
	public void update(Graphics g) {
		//System.out.println(Game1Canvas.instance.getWidth());
		paint(g);
	}
	@Override
	public void paint(Graphics g) {
		// 그림을 그릴 수 있는 곳은 window(=canvas)와 image다.
		Image buf = this.createImage(getWidth(), getHeight());
		Graphics bg = buf.getGraphics(); //그림을 그릴 수 있는 도구

		background.paint(bg);
		playBtn.paint(bg);
		title.paint(bg);
		//for (int i = 0; i < family.length; i++) {
		//	family[i].paint(bg);
//			System.out.println("family"+i+"DX : "+family[i].getDx());
//			System.out.println("family"+i+"DY : "+family[i].getDy());
//			System.out.println("family"+i+"X : "+family[i].getX());
//			System.out.println("family"+i+"Y : "+family[i].getY());
		//}
		g.drawImage(buf, 0, 0, this);

	}


	
	
}
