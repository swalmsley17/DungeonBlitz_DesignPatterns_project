package Character;

import Bonus.Bonusable;

public class Player extends Character {

	protected Player(String name, int maxHealth) {
		super(maxHealth);
		this.name = name;
	}	
	
	protected void kill()
	{
		this.health = 0;
	}
	
	@Override
	public void takeDamage(int damage)
	{
		if(!this.weapon.attackBlocked())
			super.takeDamage(damage);
	}
	
	public void useBonus(Bonusable bonus) {
		bonus.useBy(this);
	}
	
	public void increaseAttackBy(int value) {
		this.weapon.increaseDamage(value);
	}

	public void increaseBlockkBy(double value) {
		this.weapon.increaseBlockChance(value);
	}

	public void increaseHealthBy(int value) {
		this.maxHealth += value;
		this.health = this.maxHealth;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", health=" + health + ", maxHealth="
				+ maxHealth + ", weapon=" + weapon + "]";
	}
}
