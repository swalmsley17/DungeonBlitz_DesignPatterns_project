package Weapon;

import java.util.Random;

public class Weapon {

	protected int damageMin, damageMax;
	protected double accuracy, blockChance;

	protected Random gen;
	private String name;

	public Weapon(String name, int damageMin, int damageMax, double accuracy,
			double blockChance) {
		this.name = name;
		this.damageMin = damageMin;
		this.damageMax = damageMax;
		this.accuracy = accuracy;
		this.blockChance = blockChance;
		this.gen = new Random();
	}

	public boolean attackLanded() {
		return this.gen.nextDouble() < this.accuracy;
	}

	public boolean attackBlocked() {
		return this.gen.nextDouble() < this.blockChance;
	}

	public int getDamage() {
		return this.gen.nextInt(this.damageMax - this.damageMin)
				+ this.damageMin + 1;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		String result = "";

		result += "Weapon: " + this.name + "\n";
		result += "DamageMin: " + this.damageMin + "\n";
		result += "DamageMax: " + this.damageMax + "\n";
		result += "Accuracy: " + this.accuracy + "\n";
		result += "BlockChance: " + this.blockChance + "\n";

		return result;
	}
	
	public void increaseBlockChance(double value) {
		this.blockChance += value;
		
		if(this.blockChance >= 1)
			this.blockChance = .99;
	}
	
	public void increaseDamage(int value) {
		this.damageMax += value;
		this.damageMin += value;
	}

}
