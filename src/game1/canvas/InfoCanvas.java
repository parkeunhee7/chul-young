package game1.canvas;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game1.entity.MainButton;

public class InfoCanvas extends Canvas{
	public static Canvas instance;
	private Image info1Img;
	private Image info2Img;
	private MainButton nextButton;
	private boolean next;
	public InfoCanvas() {
		
		instance=this;
		nextButton = new MainButton(1700, 800, 150,150, 2);
		try {
			info1Img = ImageIO.read(new File("res/menu1.png"));
			info2Img = ImageIO.read(new File("res/menu2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {

					repaint();

					try {
						Thread.sleep(17); //(1000/60) 60fps 
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				
				
				if(!next&&isFocusable()&&code==KeyEvent.VK_ENTER)
					next=true;
				else if(next&&isFocusable()&&code==KeyEvent.VK_ENTER)
					try {
						GameFrame.instance.switchCanvas(InfoCanvas.this, GameCanvas.class);
						
					} catch (InstantiationException | IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x= e.getX();
				int y= e.getY();
				
				if(!next&&nextButton.isSelected(x, y))
					next=true;
				else if(next&&nextButton.isSelected(x, y))
					try {
						GameFrame.instance.switchCanvas(InfoCanvas.this, GameCanvas.class);
					} catch (InstantiationException | IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

			}
		});
		
	}
	@Override
	public void update(Graphics g) {
		paint(g);
	}
	@Override
	public void paint(Graphics g) {
				Image buf = this.createImage(getWidth(), getHeight());
				Graphics bg = buf.getGraphics(); //그림을 그릴 수 있는 도구
				if(!next)
					bg.drawImage(info1Img, 0, 0, GameFrame.instance.getWidth(), GameFrame.instance.getHeight(), 0, 0, 1056, 793, this.instance);
				else if(next)
					bg.drawImage(info2Img, 0, 0, GameFrame.instance.getWidth(), GameFrame.instance.getHeight(), 0, 0, 1058, 789, this.instance);
				nextButton.paint(bg);
				g.drawImage(buf, 0, 0, this);

	}

}
