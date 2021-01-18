package game1.canvas;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;

import game1.entity.Card;
import game1.entity.GameBackground;
import game1.entity.GameBoard;
import game1.entity.GameBoardListner;
import game1.entity.GameEnd;
import game1.entity.Item;
import game1.entity.ScoreBoard;
import game1.entity.Timer;
import game1.entity.TimerListener;
import game1.entity.Unit;
import game1.entity.UnitListener;
import game1.entity.UnitScore;


public class GameCanvas extends Canvas {

	private static final int SPEED = 1; //아이템 타입
	private static final int STOP = 2;
	private static final int HIDE = 3;
	private static final int WIND = 4;
	private static final int REVERSE = 5;
	public static Canvas instance;
	private Timer timer;
	private GameBoard gameBoard;
	private GameBackground background;

	private int cardSize; // 전체 카드 개수(30개까지)
	private Unit[] units;
	private Card[] cards; 
	private Unit boy;
	private Unit girl;
	private List<Item> itemList;

	private ScoreBoard scoreBoard;
	private UnitScore boyScore;
	private UnitScore girlScore;
	private UnitScore boyScoreScale;
	private UnitScore girlScoreScale;
	private int boyScoreScaleSize = 0;
	private int girlScoreScaleSize = 0;
	private GameEnd gameEnd;

	public GameCanvas() throws IOException {

		instance=this;
		background = new GameBackground(0,0,"res/game_background2.jpg");
		gameBoard = new GameBoard(0,0); // 모드1: 순서대로 클릭
		gameBoard.cardSort();
		cardSize = gameBoard.getCardSize();
		units = gameBoard.getUnits();
		cards = gameBoard.getCards();
		boy = gameBoard.getUnits()[0];
		girl = gameBoard.getUnits()[1];
		itemList = gameBoard.getItemList();
		timer = new Timer(200, 0);
		//스코어 현황판
		scoreBoard = new ScoreBoard();
		boyScore = scoreBoard.getUnitScores()[0];
		girlScore = scoreBoard.getUnitScores()[1];
		boyScoreScale = scoreBoard.getUnitScores()[2];
		girlScoreScale = scoreBoard.getUnitScores()[3];

		gameEnd = new GameEnd();


		timer.setListener(new TimerListener() {


			@Override
			public void unitMovable() {
				for (int i = 0; i <units.length; i++) {
					units[i].setMovable(true);
				}
			}

			@Override
			public void itemCreate() {

				Item newItem = gameBoard.createItem();
				itemList.add(newItem);
			}

			@Override
			public void gameOver() {
				for (int i = 0; i < units.length; i++) { //캐릭터 이동 정지
					units[i].setCharSpeed(0);
				}

				gameEnd.setGameEnd(true);


				new java.util.Timer().schedule(new TimerTask() {

					@Override
					public void run() {
						GameFrame.instance.switchCanvas(GameCanvas.this, gameBoard.getInputBoyIndex(),gameBoard.getInputGirlIndex());

					}
				}, 5000);
			}
		});


		boy.setUnitListener(new UnitListener() {

			@Override
			public void pick() {
				int x = boy.getX();
				int y = boy.getY();
				GameFrame.getInstance().effect("Card");

				for (int i = 0; i < cardSize; i++) {
					if(cards[i].isSelected(x, y) && cards[i].isHide() != true ) { //선택되면
						cards[i].setBackType(3);    // 뒷면 카드타입 선택
						if(gameBoard.setNumCheck("BOY", cards[i].getNumber()) == true ) {
							cards[i].setHide(true);     // 뒤집기							
						}
					}
				}
			}

			@Override
			public void itemPick() {
				int x = boy.getX();
				int y = boy.getY();
				for (Item item : itemList) {
					if(item.isSelected(x, y)&&(!boy.isItemFull())) {
						itemList.remove(item);
						boy.pickItem(item);
						break;
					}
				}
			}

			@Override
			public void itemEffect(Item item) {
				int type = item.getType();
				System.out.println("type:"+type);
				switch (type) {
				case SPEED: 
					boy.setCharSpeed(boy.getCharSpeed()+5);
					new java.util.Timer().schedule(new TimerTask() {

						@Override
						public void run() {
							boy.setCharSpeed(5);
						}
					}, 5000);
					break;
				case STOP:
					girl.setCharSpeed(0);
					new java.util.Timer().schedule(new TimerTask() {

						@Override
						public void run() {
							girl.setCharSpeed(5);
						}
					}, 3000);
					break;
				case HIDE:
					girl.setHide(true);
					new java.util.Timer().schedule(new TimerTask() {

						@Override
						public void run() {
							girl.setHide(false);
						}
					}, 3000);
					break;
				case WIND:
					girl.setX(10*120+350+22);
					girl.setY(777);
					break;
				case REVERSE:
					girl.setResetKey();
					girl.setReverseKey(true);
					new java.util.Timer().schedule(new TimerTask() {

						@Override
						public void run() {
							girl.setResetKey();
							girl.setReverseKey(false);

						}
					}, 5000);
					break;
				}				
			}

			@Override
			public void showItem(Item item,int index) {
				scoreBoard.getBoyItemFrame().setItemImgs(item.getImg(), index);
			}

			@Override
			public void removeItem() {
				scoreBoard.getBoyItemFrame().setItemImgs(null, 0);
				if(scoreBoard.getBoyItemFrame().getItemImg(1)!=null) { // 두번째 아이템이 존재한다면
					scoreBoard.getBoyItemFrame().setItemImgs(scoreBoard.getBoyItemFrame().getItemImg(1), 0);
					scoreBoard.getBoyItemFrame().setItemImgs(null, 1);
				} 
			}

		});



		girl.setUnitListener(new UnitListener() {

			@Override
			public void pick() {
				int x = girl.getX();
				int y = girl.getY();
				GameFrame.getInstance().effect("Card");

				for (int i = 0; i < cardSize; i++) {
					if(cards[i].isSelected(x, y) && cards[i].isHide() != true ) { //선택되면
						cards[i].setBackType(2);    // 뒷면 카드타입 선택
						if(gameBoard.setNumCheck("GIRL", cards[i].getNumber()) == true ) {

							cards[i].setHide(true);     // 뒤집기							
						}
					}
				}
			}

			@Override
			public void itemPick() {
				int x = girl.getX();
				int y = girl.getY();
				for (Item item : itemList) {
					if(item.isSelected(x, y)&&(!girl.isItemFull())) {
						itemList.remove(item);
						girl.pickItem(item);
						break;
					}
				}
			}

			@Override
			public void itemEffect(Item item) {
				int type = item.getType();
				System.out.println("type:"+type);
				switch (type) {
				case SPEED: 
					girl.setCharSpeed(girl.getCharSpeed()+5);
					new java.util.Timer().schedule(new TimerTask() {

						@Override
						public void run() {
							girl.setCharSpeed(5);
						}
					}, 5000);
					break;
				case STOP:
					boy.setCharSpeed(0);
					new java.util.Timer().schedule(new TimerTask() {

						@Override
						public void run() {
							boy.setCharSpeed(5);
						}
					}, 3000);
					break;
				case HIDE:
					boy.setHide(true);
					new java.util.Timer().schedule(new TimerTask() {

						@Override
						public void run() {
							boy.setHide(false);
						}
					}, 3000);
					break;
				case WIND:
					boy.setX(252);
					boy.setY(177);
					break;
				case REVERSE:
					boy.setResetKey();
					boy.setReverseKey(true);
					new java.util.Timer().schedule(new TimerTask() {

						@Override
						public void run() {
							boy.setResetKey();
							boy.setReverseKey(false);
						}
					}, 5000);
					break;
				}
			}


			@Override
			public void showItem(Item item, int index) {
				scoreBoard.getGirlItemFrame().setItemImgs(item.getImg(), index);
			}

			@Override
			public void removeItem() {
				scoreBoard.getGirlItemFrame().setItemImgs(null, 0);
				if(scoreBoard.getGirlItemFrame().getItemImg(1)!=null) { // 두번째 아이템이 존재한다면
					scoreBoard.getGirlItemFrame().setItemImgs(scoreBoard.getGirlItemFrame().getItemImg(1), 0);
					scoreBoard.getGirlItemFrame().setItemImgs(null, 1);
				} 
			}
		});

		gameBoard.setGameBoardListner(new GameBoardListner() {

			@Override
			public void onScoreUp(int scoreIndex, String sex) {
				switch (sex) {
				case "BOY":
					boyScore.setScoreIndex(scoreIndex);
					if( boyScore.isScale() == true ) {
						boyScoreScaleSize++;
						boyScoreScale.setScoreIndex(boyScoreScaleSize);
					}
					break;
				case "GIRL":
					girlScore.setScoreIndex(scoreIndex);
					if( girlScore.isScale() == true ) {
						girlScoreScaleSize++;
						girlScoreScale.setScoreIndex(girlScoreScaleSize);
					}
					break;
				default:
					break;
				}

			}

			@Override
			public void gameOver() {
				for (int i = 0; i < units.length; i++) { //캐릭터 이동 정지
					units[i].setCharSpeed(0);
				}

				gameEnd.setGameEnd(true);

				new java.util.Timer().schedule(new TimerTask() {

					@Override
					public void run() {
						GameFrame.instance.switchCanvas(GameCanvas.this, gameBoard.getInputBoyIndex(),gameBoard.getInputGirlIndex());
					}
				}, 5000);
			}
		});

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				switch (code) {
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_UP:
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_ENTER:
					if(girl.isMovable())
						girl.move(code);
					break;
				case KeyEvent.VK_SHIFT:
					if(!girl.isItemEmpty())
						girl.useItem(code);
					break;
				}

				switch (code) {
				case KeyEvent.VK_A:
				case KeyEvent.VK_W:
				case KeyEvent.VK_D:
				case KeyEvent.VK_S:
				case KeyEvent.VK_G:
					if(boy.isMovable())
						boy.move(code);
					break;
				case KeyEvent.VK_H:
					if(!boy.isItemEmpty())
						boy.useItem(code);
					break;
				}

			}


			@Override
			public void keyReleased(KeyEvent e) {
				int code = e.getKeyCode();
				boy.stop(code);
				girl.stop(code);
			}
		});




		new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					repaint();
					//System.out.println("repaint");

					try {
						Thread.sleep(16); //(1000/60) 60fps 
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		//GameFrame.getInstance().setBgm(false);
		//GameFrame.getInstance().bgmOff();

	}




	@Override
	public void update(Graphics g) {
		//System.out.println(Game1Canvas.instance.getWidth());
		//System.out.println(System.currentTimeMillis());
		timer.update();
		gameBoard.update();
		scoreBoard.update();

		paint(g);
	}
	@Override
	public void paint(Graphics g) {
		// 그림을 그릴 수 있는 곳은 window(=canvas)와 image다.
		Image buf = this.createImage(getWidth(), getHeight());
		Graphics bg = buf.getGraphics(); //그림을 그릴 수 있는 도구

		background.paint(bg);
		//scoreBoard.paint(bg);
		gameBoard.paint(bg);
		timer.paint(bg);
		scoreBoard.paint(bg);
		gameEnd.paint(bg);

		g.drawImage(buf, 0, 0, this);

	}



}




