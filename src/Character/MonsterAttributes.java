package Character;

import java.util.Random;

public class MonsterAttributes {
	private double healChance;
	private int healMin, healMax;
	private Random gen;
	
	public MonsterAttributes(double healChance, int healMin, int healMax)
	{
		this.healChance = healChance;
		this.healMin = healMin;
		this.healMax = healMax;
		
		this.gen = new Random();
	}
	
	public int tryHeal()
	{
		int healAmount = 0;
		if(this.gen.nextDouble() < this.healChance)
			healAmount = this.gen.nextInt(healMax) + this.healMin + 1;
		return healAmount;
	}
}
