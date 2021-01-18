package game1.entity;

public interface UnitListener {

	void pick();
	
	void itemPick();

	void itemEffect(Item item);

	void showItem(Item item, int index);

	void removeItem();
}
