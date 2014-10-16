package Model;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import Character.Enemy;
import Character.Player;

public interface ModelInterface {
	public int[][] getMap();
	public Point getPlayerPoint();
	public int setPlayerPoint(Point hero);
	public ArrayList<Enemy> randomEnemy();
	public void printMap();
	public Player getPlayer();
	public Image getTopImage();
	public Image getBottomImage();
	public void lookEast();
	public void lookWest();
	public void resume();
	public void pause();
	public Image getHealthBonus();
	public Image getAttackBonus();
	public Image getBlockBonus();
}
