package game1.entity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class ResultButton extends ButtonItem {
	
	private static final int PLAY = 1;
	private static final int OPTION = 2;
	private static final int EXIT = 3;
	private Image img;
	private Image changeImg;
	private Image basicImg;
	private int imgType;
	
	


	
		
	
	public ResultButton() { //기본 생성자
		this(200, 400, 291, 95, 2);
	}
	public ResultButton(int x, int y, int width, int height,int imgType) {
		super(x, y, width, height);
		this.imgType = imgType;
		try {
			switch (this.imgType) {
			case PLAY:
				this.img = ImageIO.read(new File("res/play.png"));
				this.changeImg = ImageIO.read(new File("res/play_change.png"));
				this.basicImg = ImageIO.read(new File("res/play.png"));
				break;
			case OPTION:
				this.img = ImageIO.read(new File("res/option.png"));
				this.changeImg = ImageIO.read(new File("res/option_change.png"));
				this.basicImg = ImageIO.read(new File("res/option.png"));
				break;
			case EXIT:
				img = ImageIO.read(new File("res/exit.png"));
				changeImg = ImageIO.read(new File("res/exit_change.png"));
				basicImg = ImageIO.read(new File("res/exit.png"));
				break;
			default:
				System.out.println("이미지타입 선택하세요");
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





