package Character;

import java.util.ArrayList;
import java.util.Random;

public class EnemyGroup {

	private EnemyFactory factory;

	private ArrayList<Enemy> group;

	Random gen;

	private String groupLevel;

	public EnemyGroup(int level) {
		this.group = new ArrayList<Enemy>();

		if (level == 1) {
			this.groupLevel = "Level_One";
			this.factory = new LevelOneEnemyFactory();
		} else {
			this.factory = new LevelTwoEnemyFactory();
			this.groupLevel = "Level_Two";
		}

		this.gen = new Random();

		int groupSize = gen.nextInt(3) + 2;

		for (int i = 0; i < groupSize; i++)
			this.group.add(this.factory.randomEnemy());
	}

	public Enemy get(int index) {
		if (index < 0 || index >= this.group.size())
			throw new IndexOutOfBoundsException();
		return this.group.get(index);
	}

	public void remove(int index) {
		if (index < 0 || index >= this.group.size())
			throw new IndexOutOfBoundsException();
		this.group.remove(index);
	}
	
	public void healExcluding(int index) {
		for(int i = 0; i < this.group.size(); i++) {
			if(i != index)
				this.group.get(i).getAttributes().tryHeal();
		}
	}

	@Override
	public String toString() {
		String result = this.groupLevel + "\n";
		for (int i = 0; i < this.group.size(); i++)
			result += this.group.get(i).toString();
		return result;
	}

}
