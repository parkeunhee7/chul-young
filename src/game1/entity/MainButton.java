package game1.entity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class MainButton extends ButtonItem {
	
	private static final int PLAY = 1;
	private static final int NEXT = 2;
	private static final int RETRY = 3;
	private static final int HOME = 4;
	private Image img;
	private Image changeImg;
	private Image basicImg;
	private int imgType;
	
	


	
		
	
	public MainButton() { //기본 생성자
		this(200, 400, 291, 95, 2);
	}
	public MainButton(int x, int y, int width, int height,int imgType) {
		super(x, y, width, height);
		this.imgType = imgType;
		try {
			switch (this.imgType) {
			case PLAY:
				img = ImageIO.read(new File("res/start_red.png"));
				changeImg = ImageIO.read(new File("res/start_blue.png"));
				basicImg = ImageIO.read(new File("res/start_red.png"));
				break;
			case NEXT:
				img = ImageIO.read(new File("res/next.png"));
				changeImg = ImageIO.read(new File("res/next.png"));
				basicImg = ImageIO.read(new File("res/next.png"));
				break;
			case RETRY:
				img = ImageIO.read(new File("res/retry.png"));
				changeImg = ImageIO.read(new File("res/retry_change.png"));
				basicImg = ImageIO.read(new File("res/retry.png"));
				break;
			case HOME:
				img = ImageIO.read(new File("res/home.png"));
				changeImg = ImageIO.read(new File("res/home_change.png"));
				basicImg = ImageIO.read(new File("res/home.png"));
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setImg(img);
		setChangeImg(changeImg);
		setBasicImg(basicImg);
		

	}




	
	@Override
	protected Image getImage() { //이미지 받아오기
		return img;
	}
	@Override
	protected Image getChangeImage() {
		// TODO Auto-generated method stub
		return changeImg;
	}
	@Override
	protected Image getBasicImage() {
		// TODO Auto-generated method stub
		return basicImg;
	}
	
		
	}





