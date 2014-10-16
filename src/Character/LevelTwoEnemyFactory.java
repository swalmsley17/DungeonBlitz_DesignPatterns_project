package Character;

import java.awt.Image;
import java.awt.Point;

import AnimatingImage.Animation;
import Weapon.WeaponFactory;
import Weapon.Weapon;
import java.net.URL;
import java.util.Random;

public class LevelTwoEnemyFactory implements EnemyFactory {

	@Override
	public Enemy randomEnemy() {
		Enemy newEnemy;

		Random gen = new Random();
		int chance = gen.nextInt(3);

		if (chance == 0)
			newEnemy = createEnemy("Acromantula");
		else if (chance == 1)
			newEnemy = createEnemy("BloodWraith");
		else
			newEnemy = createEnemy("DreadKnight");
		return newEnemy;
	}

	@Override
	public Enemy createEnemy(String name) {
		Enemy newEnemy;
		EnemyAttributes newAttributes;

		WeaponFactory factory = new WeaponFactory();
		Weapon newWeapon;

		URL newURL;
		Image newImage;

		if (name.equals("Acromantula")) {
			newEnemy = new Enemy("Acromantula", 150);
//			newEnemy.setSprite("SpritesImage/Skeleton.png"); have not found sprite
			
			newAttributes = new EnemyAttributes(.75, 10, 20);
			newWeapon = factory.createWeapon("Fangs");

		} else if (name.equals("BloodWraith")) {
			newEnemy = new Enemy("BloodWraith", 140);
			newEnemy.setSprite("SpritesImage/Ghost.png");
			newEnemy.setWalking(new Animation(newEnemy.getSprite(), new Point(50, 50), 8, 100,new Point( 7, 650), new Point(60, 732),107)); 
			
			newAttributes = new EnemyAttributes(.7, 10, 25);
			newWeapon = factory.createWeapon("Scythe");

		} else if (name.equals("DreadKnight")) {
			newEnemy = new Enemy("DreadKnight", 175);
//			newEnemy.setSprite("SpritesImage/Skeleton.png"); have not found sprite
			
			newAttributes = new EnemyAttributes(.3, 20, 35);
			newWeapon = factory.createWeapon("BastardSword");

		}
		// IF THIS SHOWS UP DURING RUNTIME SOMETHING MESSED UP
		else {
			newEnemy = new Enemy("MISINGNO_2", 1000);
			newAttributes = new EnemyAttributes(.5, 50, 100);
			newWeapon = factory.createWeapon("MISINGNO_WEAPON_2");
			// newURL = something;
			// newImage = something;
		}

		newEnemy.setAttributes(newAttributes);
		newEnemy.setWeapon(newWeapon);
		// newEnemy.setURL(newURL);
		// newEnemy.setImage(newImage);
		return newEnemy;
	}

}
