package game1.entity;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;

import game1.canvas.GameFrame;

public class GameBoard {
	private int x;
	private int y;

	private Card[] startCards;
	private Card[] cards;
	private int cardCol = 0;  // 
	private int cardRow = 10;  // 가로로 놓을 카드 개수
	private int cardSize = 50; // 전체 카드 개수(60개까지)
	private int cardHideSize = 50;
	
	private int[] boyInputNum;
	private int[] girlInputNum;
	private int[] boyTrueNum;
	private int[] girlTrueNum;
	private int inputBoyIndex;
	private int inputGirlIndex;

	private int betWidth = 120;
	private int betHeight = 150;


	private Unit[] units;
	private Unit boy;
	private Unit girl;
	private List<Item> itemList;
	private GameBoardListner gameBoardListner;
	private int unitSize;


	public GameBoard() {
		this(0, 0);
	}

	public GameBoard(int x, int y ) {

		this.x = x; 
		this.y = y;
		startCards = new Card[2];

		cards = new Card[cardSize];

		boy = new Unit(-betWidth+350+22, 100+77, "res/character1.png"); // +22 ,+77
		girl = new Unit((cardRow)*betWidth+350+22, 777, "res/character2.png");
		units = new Unit[2];
		units[0] = boy;
		units[1] = girl;
		itemList = new ArrayList<>();

		startCards[0] = new Card(-betWidth+350, 100, 1);
		startCards[1] = new Card((cardRow)*betWidth+350, 700, 2);
		for (int i = 0; i < startCards.length; i++) {
			startCards[i].setHide(true);
			startCards[i].setBackType(1);

		}

		unitSize = 2;

		// 입력받는 카드 순서 인덱스
		boyTrueNum = new int[cardSize]; // player1의 정답 number
		girlTrueNum = new int[cardSize];// player2의 정답 number
		boyInputNum = new int[cardSize]; // player1의 입력 number
		girlInputNum = new int[cardSize];// player2의 입력 number

		// 정답지
		for (int i = 0; i < cardSize; i++) {
			boyTrueNum[i] = i + 1;
		}
		for (int i = 0; i < cardSize; i++) {
			girlTrueNum[i] = cardSize - i;
		}
	}






	public void setGameBoardListner(GameBoardListner gameBoardListner) {
		this.gameBoardListner = gameBoardListner;
	}

	public void update() {
		boy.update();
		girl.update();
		for (Item item : itemList) {
			item.update();
		}
		
//		if(allHide())
//			gameBoardListner.gameOver();

	}
	
	
	public void show(int i) {
		cards[i].setHide(false);

	}


	public void cardSort() { //카드를 생성하고 카드판에 배열하는 부분
		for (int i = 0; i < cardSize; i++) {
			cards[i] = new Card((i%cardRow)*betWidth+350, cardCol*betHeight+100,(i+1));
			System.out.println();
			if(i%cardRow==(cardRow-1)) // row의 맨 마지막에 도착하면 줄 하나 추가
				cardCol++;
		}

		for (int j = 0; j< 100; j++) { //100번 섞어준다.
			for (int i = 0; i < cardSize; i++) {
				int ranNum1 = (int)(Math.random()*cardSize);
				int ranNum2 = (int)(Math.random()*cardSize);
				int temp;
				temp = cards[ranNum1].getNumber();
				cards[ranNum1].setNumber(cards[ranNum2].getNumber());
				cards[ranNum2].setNumber(temp);
			}
		}



	}



	public void CardHide() {
		for (int i = 0; i < cardSize; i++) {
			cards[i].setHide(true);
		}

	}

	public void paint(Graphics g) {
		for (int i = 0; i < 2; i++) {
			startCards[i].paint(g);
		}
		for (int i = 0; i < cardSize; i++) {
			cards[i].paint(g);
		}
		for (Item item : itemList) {
			item.paint(g);
		}
		boy.paint(g);
		girl.paint(g);
	}



	public boolean setNumCheck(String player, int number) {
		for (int i = 0; i < cardSize; i++) {
			switch (player) {
			case "BOY":
				if (number == boyTrueNum[inputBoyIndex]) {
					boyInputNum[inputBoyIndex] = number;
					inputBoyIndex++;
					cardHideSize--;
					gameBoardListner.onScoreUp(inputBoyIndex, "BOY");
					if(cardHideSize==0)
						gameBoardListner.gameOver();
					return true;
				}
				break;
			case "GIRL":
				if (number == girlTrueNum[inputGirlIndex]) {
					girlInputNum[inputGirlIndex] = number;
					inputGirlIndex++;
					cardHideSize--;
					gameBoardListner.onScoreUp(inputGirlIndex, "GIRL");
					if(cardHideSize==0)
						gameBoardListner.gameOver();

					return true;
				}
				break;
			default:
				break;
			}
		}
		return false;
	}



	public Item createItem() {
		Item item = null;
		try {
			item = new Item();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}

	public int getCardSize() {
		return cardSize;
	}

	public void setCardSize(int cardSize) {
		this.cardSize = cardSize;
	}

	public Unit getBoy() {
		return boy;
	}

	public Unit getGirl() {
		return girl;
	}

	public Card[] getCards() {
		return cards;
	}

	public Unit[] getUnits() {
		return units;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public int getInputBoyIndex() {
		return inputBoyIndex;
	}

	public int getInputGirlIndex() {
		return inputGirlIndex;
	}







}
