package Bonus;

import Character.Player;


public class BlockBonus implements Bonusable{
	private double value;
	
	public BlockBonus(double value) {
		this.value = value;
	}
	
	public void useBy(Player character) {
		character.increaseBlockkBy(value);
	}
}
