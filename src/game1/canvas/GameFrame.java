package game1.canvas;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameFrame extends Frame {
	public static GameFrame instance;
	private IntroCanvas canvas;
	private GameCanvas gameCanvas;


	// sound
	private Clip bgClip;
	private Clip effectClip;
	private AudioInputStream bgAis;
	private AudioInputStream effectAis;
	private boolean isEffect;
	private boolean isBgm;


	public GameFrame() {
		instance = this;

		isEffect = true;
		isBgm = true;



		canvas = new IntroCanvas();
		//canvas.start();
		//gameCanvas = new GameCanvas();
		//gameCanvas.start();
		setExtendedState(JFrame.MAXIMIZED_BOTH); // 전체화면

		setSize(1000,1000);
	//	setExtendedState(JFrame.MAXIMIZED_BOTH); // 전체화면
		setVisible(true);
		add(canvas);
		setTitle("철수와 영희");
		//add(gameCanvas);


		// sound
		mainSound("res/sound/game_bgm.wav");
		//		effect();


		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int input = JOptionPane.showConfirmDialog(GameFrame.this, "종료합니다", "종료", JOptionPane.OK_CANCEL_OPTION);
				if(input==0)
					System.exit(0);
			}
		});
	}

	// bgSound
	 public void mainSound(String file) {
		if (isBgm) {
			try {
				bgAis = AudioSystem.getAudioInputStream(new File(file));
				bgClip = AudioSystem.getClip();

				bgClip.open(bgAis);
				bgClip.start();
				bgClip.loop(Integer.MAX_VALUE);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// effectSound
	public void effect(String something) {
		if (isEffect) {
			try {
				if(something=="Button")
					effectAis = AudioSystem.getAudioInputStream(new File("res/sound/btn_click.wav"));
				else if (something=="Card")
					effectAis = AudioSystem.getAudioInputStream(new File("res/sound/card_click.wav"));
				else if (something=="ItemPick")
					effectAis = AudioSystem.getAudioInputStream(new File("res/sound/item_pick.wav"));
				else if (something=="ItemUse")
					effectAis = AudioSystem.getAudioInputStream(new File("res/sound/item_use.wav"));
				effectClip = AudioSystem.getClip();
				effectClip.open(effectAis);
				effectClip.start();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}




	public void switchCanvas(Canvas oldCanvas, Class newCanvas) throws InstantiationException, IllegalAccessException {
		Canvas canvas = (Canvas)newCanvas.newInstance();

		add(canvas);
		revalidate();
		//		if(canvas instanceof GameCanvas)
		//			((GameCanvas)canvas).start();
		canvas.setFocusable(true);
		canvas.requestFocus();

		remove(oldCanvas);
	}
	
	public void switchCanvas(Canvas oldCanvas, int boyResult, int girlResult) {
		
		Canvas canvas;
			canvas = new ResultCanvas(boyResult, girlResult);
		add(canvas);
		canvas.setFocusable(true);
		canvas.requestFocus();
		revalidate();
		remove(oldCanvas);
	}

	public boolean isBgm() {
		return isBgm;
	}

	public void setBgm(boolean isBgm) {
		this.isBgm = isBgm;
	}

	public static GameFrame getInstance() {
		return instance;
	}

	public void setBgClip(Clip bgClip) {
		this.bgClip = bgClip;
	}

	public void bgmOn() {
		bgClip.start();
	}
	public void bgmOff() {
		bgClip.stop();
	}
	public void setEff(boolean b) {
		isEffect = b;
	}

	public void effectStart() {
		if(isEffect == true)
			effectClip.loop(1);
	}

	
	
}
