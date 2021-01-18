package game1.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game1.canvas.GameCanvas;
import game1.canvas.GameFrame;

public class Unit {
	private static final int Girl_UP = 38;
	private static final int Girl_DOWN = 40;
	private static final int Girl_LEFT = 37;
	private static final int Girl_RIGHT = 39;
	private static final int Girl_PICK = 10;
	private static final int Girl_USE = 16;

	private static final int Boy_UP = 87;
	private static final int Boy_DOWN = 83;
	private static final int Boy_LEFT = 65;
	private static final int Boy_RIGHT = 68;
	private static final int Boy_PICK = 71;
	private static final int Boy_USE = 72;
	private int N = 0;
	private int W = 0;
	private int E = 0;
	private int S = 0;
	private int x;
	private int y;
	private final int width;
	private final int height;
	private Image img;
	private String url;
	private int xIndex;
	private int yIndex;
	private int charSpeed;
	private int timeTempo;
	private boolean movable;
	private boolean itemFull;
	private UnitListener unitListener;
	private Item[] items;
	private int itemSize;
	private int itemIndex;
	private boolean itemEmpty;
	private boolean hide;
	private boolean reverseKey;
	public Unit() {
		this(0, 0, "");
	}

	public Unit(int x, int y, String url) {
		this.x = x;
		this.y = y;
		this.url = url;
		width = 64;
		height = 64;
		timeTempo = 12;
		charSpeed = 5;
		movable = false;
		itemEmpty = true;
		itemFull = false;
		hide = false;
		itemSize = 2;
		itemIndex = 0;
		items = new Item[itemSize];
		try {
			img = ImageIO.read(new File(url));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update() {
		charSpeed = getCharSpeed();
		if(timeTempo==0 &&(W==0&&E==0&&S==0&&N==0)) // ���� ���� �� ����
			xIndex=0;
		else if(timeTempo==0 &&(W==1||E==1||S==1||N==1)) { //�����϶��� �ε��� ��ȭ
			if(xIndex%4==3)
				xIndex=0;
			xIndex++;
			timeTempo=12;
		}
		else
			timeTempo--;
		
//		System.out.println(GameFrame.getInstance().getHeight()); //1936 *1056
// ĵ���� ���� ����� �� ���� �ڵ�
		if(5>=y) 
			setY(10);
		else if(y>1000)
			setY(995);
		
		if(0>=x)
			setX(5);
		else if(x>1805)
			setX(1800);
		
		

		if(N==1)
			this.y = y-charSpeed;
		if (S==1)
			this.y = y+charSpeed;
		if (E==1)
			this.x = x+charSpeed;
		if (W==1)
			this.x = x-charSpeed;
		
		if(items[0]==null)
			itemEmpty = true;
		else
			itemEmpty = false;
	}

	public void pickItem(Item item) {
		GameFrame.getInstance().effect("ItemPick");
		if(!itemFull) {
			items[itemIndex]= item;
			itemIndex++;
		}
		if(itemIndex==itemSize) {
			itemFull=true;
			System.out.println("�������� ����á���ϴ�.");
		}
		else
			itemFull=false;
		
		unitListener.showItem(item,itemIndex-1);
	}

	public void useItem(int key) {
		GameFrame.getInstance().effect("ItemUse");
		Item item = null;
		itemFull=false;
		System.out.println("������ ���");
		if(!itemEmpty) {
			if(items[1]!=null) {
				item = items[0];
				items[0] = items[1];
				items[1] = null;
				itemIndex--;
			}
			else if (items[0]!=null) {
				item = items[0];
				items[0] = null;
				itemIndex--;
			}
			unitListener.removeItem();
		}
		else if(itemEmpty) {
			item = null;
			System.out.println("����� �������� �����ϴ�");
		}
		System.out.println("index:"+itemIndex);
		unitListener.itemEffect(item);


		//		if(this.getUrl().equals("res/character1.png")&& key==Boy_USE)
		//			unitListener.itemEffect(item);
		//		else if(this.getUrl().equals("res/character2.png")&& key==Girl_USE)
		//			unitListener.itemEffect(item);
	}

	public void move(int key) {
		unitListener.itemPick();
		if(!reverseKey) {
			if(this.getUrl().equals("res/character1.png")) {
				switch (key) {
				case Boy_UP: //����
					yIndex=3;
					N=1;
					break;
				case Boy_DOWN: //�Ʒ���
					yIndex=0;
					S=1;
					break;
				case Boy_LEFT: //����
					yIndex=1;
					W=1;
					break;
				case Boy_RIGHT: //������
					yIndex=2;
					E=1;
					break;
				case Boy_PICK: //������
					unitListener.pick();
					break;
				}
			}
			else if (this.getUrl().equals("res/character2.png")){
				switch (key) {
				case Girl_UP: //����
					yIndex=3;
					N=1;
					break;
				case Girl_DOWN: //�Ʒ���
					yIndex=0;
					S=1;
					break;
				case Girl_LEFT: //����
					yIndex=1;
					W=1;
					break;
				case Girl_RIGHT: //������
					yIndex=2;
					E=1;
					break;
				case Girl_PICK: //������
					unitListener.pick();
					break;
				}
			}
		}
		else if (reverseKey) {
			if(this.getUrl().equals("res/character1.png")) {
				switch (key) {
				case Boy_DOWN: //����
					yIndex=3;
					N=1;
					break;
				case Boy_UP: //�Ʒ���
					yIndex=0;
					S=1;
					break;
				case Boy_RIGHT: //����
					yIndex=1;
					W=1;
					break;
				case Boy_LEFT: //������
					yIndex=2;
					E=1;
					break;
				case Boy_PICK: //������
					unitListener.pick();
					break;
				}
			}
			else if (this.getUrl().equals("res/character2.png")){
				switch (key) {
				case Girl_DOWN: //����
					yIndex=3;
					N=1;
					break;
				case Girl_UP: //�Ʒ���
					yIndex=0;
					S=1;
					break;
				case Girl_RIGHT: //����
					yIndex=1;
					W=1;
					break;
				case Girl_LEFT: //������
					yIndex=2;
					E=1;
					break;
				case Girl_PICK: //������
					unitListener.pick();
					break;
				}
			}
		}
	}

	public void stop(int key) {
		if(!reverseKey) {
			if(this.getUrl().equals("res/character1.png")) {
				switch (key) {
				case Boy_UP: //����
					yIndex=3;
					N=0;
					break;
				case Boy_DOWN: //�Ʒ���
					yIndex=0;
					S=0;
					break;
				case Boy_LEFT: //����
					yIndex=1;
					W=0;
					break;
				case Boy_RIGHT: //������
					yIndex=2;
					E=0;
					break;
				}
			}
			else if (this.getUrl().equals("res/character2.png")){
				switch (key) {
				case Girl_UP: //����
					yIndex=3;
					N=0;
					break;
				case Girl_DOWN: //�Ʒ���
					yIndex=0;
					S=0;
					break;
				case Girl_LEFT: //����
					yIndex=1;
					W=0;
					break;
				case Girl_RIGHT: //������
					yIndex=2;
					E=0;
					break;
				}
			}
		}
		else if (reverseKey) {
			if(this.getUrl().equals("res/character1.png")) {
				switch (key) {
				case Boy_DOWN: //����
					yIndex=3;
					N=0;
					break;
				case Boy_UP: //�Ʒ���
					yIndex=0;
					S=0;
					break;
				case Boy_RIGHT: //����
					yIndex=1;
					W=0;
					break;
				case Boy_LEFT: //������
					yIndex=2;
					E=0;
					break;
				}
			}
			else if (this.getUrl().equals("res/character2.png")){
				switch (key) {
				case Girl_DOWN: //����
					yIndex=3;
					N=0;
					break;
				case Girl_UP: //�Ʒ���
					yIndex=0;
					S=0;
					break;
				case Girl_RIGHT: //����
					yIndex=1;
					W=0;
					break;
				case Girl_LEFT: //������
					yIndex=2;
					E=0;
					break;
				}
			}
		}
	}
	public void paint(Graphics g) {
		int dx1 = x-(width/2);
		int dy1 = y-(height+13);
		int dx2 = x+2*width-(width/2);
		int dy2 = y+2*height-(height+13);
		int sx1 = width*xIndex;
		int sy1 = height*yIndex;
		int sx2 = width*(xIndex+1);
		int sy2 = height*(yIndex+1);

		if(!hide)
			g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, GameCanvas.instance);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setyIndex(int yIndex) {
		this.yIndex = yIndex;
	}

	public String getUrl() {
		return url;
	}

	public void setUnitListener(UnitListener unitListener) {
		this.unitListener = unitListener;
	}

	public boolean isMovable() {
		return movable;
	}

	public void setMovable(boolean movable) {
		this.movable = movable;
	}

	public boolean isItemFull() {
		return itemFull;
	}

	public boolean isItemEmpty() {
		return itemEmpty;
	}

	public void setCharSpeed(int charSpeed) {
		this.charSpeed = charSpeed;
	}

	public int getCharSpeed() {
		return charSpeed;
	}

	public void setHide(boolean hide) {
		this.hide = hide;
	}

	public void setReverseKey(boolean reverseKey) {
		this.reverseKey = reverseKey;
	}

	public void setResetKey() {
		this.N = 0;
		this.W = 0;
		this.E = 0;
		this.S = 0;
	}





}
