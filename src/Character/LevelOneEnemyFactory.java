package Character;

import java.awt.Image;
import java.awt.Point;
import java.net.URL;
import java.util.Random;

import AnimatingImage.Animation;
import Weapon.WeaponFactory;
import Weapon.Weapon;

public class LevelOneEnemyFactory implements EnemyFactory {

	@Override
	public Enemy randomEnemy() {
		Enemy newEnemy;

		Random gen = new Random();
		int chance = gen.nextInt(3);

		if (chance == 0)
			newEnemy = createEnemy("Imp");
		else if (chance == 1)
			newEnemy = createEnemy("Goblin");
		else
			newEnemy = createEnemy("Skeleton");
		return newEnemy;
	}

	@Override
	public Enemy createEnemy(String name) {
		Enemy newEnemy;
		EnemyAttributes newAttributes;

		WeaponFactory factory = new WeaponFactory();
		Weapon newWeapon = null;


		if (name.equals("Imp")) {
			newEnemy = new Enemy("Dwarf", 100);
			newEnemy.setSprite("SpritesImage/Dwarf.png");
			newEnemy.setWalking(new Animation(newEnemy.getSprite(), new Point(0, 0), 8, 100,new Point( 10, 91), new Point(60,156),98));
			newEnemy.setFighting(new Animation(newEnemy.getSprite(), new Point(70, 70), 8, 100, new Point( 0, 165), new Point(64, 245),95));
			newEnemy.setStanding(new Animation(newEnemy.getSprite(), new Point(70, 70), 6, 200, new Point( 10, 5), new Point(57, 75),92));
			newEnemy.setGotHit(new Animation(newEnemy.getSprite(), new Point(70, 70), 3, 400, new Point( 0, 545), new Point(60, 606),92));//not done
						
			
			newAttributes = new EnemyAttributes(.5, 5, 20);
			newWeapon = factory.createWeapon("Claws");
		} else if (name.equals("Goblin")) {
			newEnemy = new Enemy("Goblin", 150);
			newEnemy.setSprite("SpritesImage/Goblin.png");
			newEnemy.setWalking(new Animation(newEnemy.getSprite(), new Point(0, 0), 8, 100,new Point( 6, 84), new Point(52, 157),111)); 
			newEnemy.setFighting(new Animation(newEnemy.getSprite(), new Point(70, 70), 6, 100, new Point( 7, 245), new Point(70, 326),50));// not great
			newEnemy.setStanding(new Animation(newEnemy.getSprite(), new Point(70, 70), 7, 350, new Point( 110, 0), new Point(163, 71),107));
			newEnemy.setGotHit(new Animation(newEnemy.getSprite(), new Point(70, 70), 2, 400, new Point( 8, 255), new Point(55, 327),102));

						
			newAttributes = new EnemyAttributes(.4, 5, 15);
			newWeapon = factory.createWeapon("Claws");

		} else if (name.equals("Skeleton")) {
			newEnemy = new Enemy("Skeleton", 75);
			newEnemy.setSprite("SpritesImage/Skeleton.png");
			newEnemy.setWalking(new Animation(newEnemy.getSprite(), new Point(0, 0), 9, 100,new Point( 8, 81), new Point(59, 155),50)); 
			newEnemy.setFighting(new Animation(newEnemy.getSprite(), new Point(70, 70), 8, 100, new Point( 5, 160), new Point(67, 253),72));//not done not good
			newEnemy.setStanding(new Animation(newEnemy.getSprite(), new Point(70, 70), 2, 400, new Point( 70, 5), new Point(130, 77),55));
			newEnemy.setGotHit(new Animation(newEnemy.getSprite(), new Point(70, 70), 2, 400, new Point( -4, 174), new Point(50, 255),70));
			
			newAttributes = new EnemyAttributes(.75, 10, 20);
			newWeapon = factory.createWeapon("Dagger");
		}
		// IF THIS SHOWS UP DURING RUNTIME SOMETHING MESSED UP
		else {
			newEnemy = new Enemy("MISINGNO_1", 1000);
			newAttributes = new EnemyAttributes(.5, 50, 100);
			newWeapon = factory.createWeapon("MISINGNO_WEAPON_1");

		}

		newEnemy.getStanding().setReflect(true);
		newEnemy.setPoint(new Point(0,0));
		newEnemy.setAttributes(newAttributes);
		newEnemy.setWeapon(newWeapon);
		// newEnemy.setURL(newURL);
		// newEnemy.setImage(newImage);
		return newEnemy;
	}

}
