package Character;

import java.awt.Point;
import java.util.Random;

import AnimatingImage.Animation;
import Weapon.Weapon;
import Weapon.WeaponFactory;

public class PlayerFactory {
	
	public Player createPlayer(String name) {
		Player newPlayer;
		
		WeaponFactory factory = new WeaponFactory();
		Weapon newWeapon = null;

		if (name.equals("Knight")) {
			newPlayer = new Player("Imp", 100);
			newPlayer.setSprite("SpritesImage/Paladin.png");
			newPlayer.setWalking(new Animation(newPlayer.getSprite(), new Point(70, 70), 8, 100,new Point( 0, 106), new Point(74, 189),125));
			newPlayer.setFighting(new Animation(newPlayer.getSprite(), new Point(70, 70), 11, 100, new Point( -1, 227), new Point(65, 309),118));
			newPlayer.setStanding(new Animation(newPlayer.getSprite(), new Point(70, 70), 7, 250, new Point( 0, 11), new Point(53, 99),120));
			newPlayer.setGotHit(new Animation(newPlayer.getSprite(), new Point(70, 70), 3, 350, new Point( 0, 320), new Point(66, 401),112));
			
			newWeapon = factory.createWeapon("BastardSword");
		} else if (name.equals("Ranger")) {
			newPlayer = new Player("Ranger", 150);
			newPlayer.setSprite("SpritesImage/Ranger.png");
			newPlayer.setWalking(new Animation(newPlayer.getSprite(), new Point(70,70), 12, 100,new Point( 0, 6), new Point(54, 75),80));
			newPlayer.setFighting(new Animation(newPlayer.getSprite(), new Point(70, 70), 13, 100, new Point( 6, 252), new Point(58, 333),76));
			newPlayer.setStanding(new Animation(newPlayer.getSprite(), new Point(70,70), 5, 100,new Point( 5, 5), new Point(49, 80),77)); 
			newPlayer.setGotHit(new Animation(newPlayer.getSprite(), new Point(70, 70), 2, 400, new Point( 8, 86), new Point(55, 158),76));
			
			newWeapon = factory.createWeapon("Bow");

		} else if (name.equals("Wizard")) {
			newPlayer = new Player("Wizard", 75);
			newPlayer.setSprite("SpritesImage/Mage.png");
			newPlayer.setWalking(new Animation(newPlayer.getSprite(), new Point(70, 70), 8, 100, new Point( 7, 650), new Point(60, 732),107));
			newPlayer.setFighting(new Animation(newPlayer.getSprite(), new Point(70, 70), 7, 100, new Point( 0, 420), new Point(75, 524),104));
			newPlayer.setStanding(new Animation(newPlayer.getSprite(), new Point(70, 70), 7, 100, new Point( -4, 0), new Point(75, 86),104));
			newPlayer.setGotHit(new Animation(newPlayer.getSprite(), new Point(70, 70), 2, 400, new Point( 3, 112), new Point(55, 196),102));

			newWeapon = factory.createWeapon("BoltStaff");
		}
		//Used for Testing
		else {
			newPlayer = new Player("GOD", 1000);
			newWeapon = factory.createWeapon("SwordOfAThousandTruths");
			newPlayer.setSprite("SpritesImage/Mage.png");
			newPlayer.setWalking(new Animation(newPlayer.getSprite(), new Point(50, 50), 8, 100, new Point( 7, 650), new Point(60, 732),107));
		}

		newPlayer.setPoint(new Point(0,0));
		newPlayer.setWeapon(newWeapon);
		return newPlayer;
	}
}
