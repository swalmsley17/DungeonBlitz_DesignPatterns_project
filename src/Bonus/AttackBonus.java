package Bonus;

import Character.Player;

public class AttackBonus implements Bonusable{
	private int value;
	
	public AttackBonus(int value) {
		this.value = value;
	}
	
	public void useBy(Player character) {
		character.increaseAttackBy(value);
	}
}
