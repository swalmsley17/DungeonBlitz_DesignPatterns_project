package Character;

public interface EnemyFactory {

	abstract Enemy randomEnemy();

	abstract Enemy createEnemy(String name);
}