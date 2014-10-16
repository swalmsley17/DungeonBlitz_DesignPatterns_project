package Bonus;

import Character.Player;

public class HealthBonus implements Bonusable{
	private int value;
	
	public HealthBonus(int value) {
		this.value = value;
	}

	public void useBy(Player character) {
		character.increaseHealthBy(value);		
	}
	
}
