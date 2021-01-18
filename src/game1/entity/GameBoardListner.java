package game1.entity;

public interface GameBoardListner {

	void onScoreUp(int scoreIndex, String sex);

	void gameOver();

}
