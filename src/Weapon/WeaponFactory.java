package Weapon;

public class WeaponFactory {

	public Weapon createWeapon(String name) {
		Weapon newWeapon;

		if (name.equals("BastardSword")) {
			newWeapon = new Weapon("Bastard Sword", 50, 65, .7, .5);
		} else if (name.equals("BoltStaff")) {
			newWeapon = new Weapon("Bolt Staff", 45, 60, .7, .25);
		} else if (name.equals("Bow")) {
			newWeapon = new Weapon("Bow", 35, 80, .85, .3);
		} else if (name.equals("Dagger")) {
			newWeapon = new Weapon("Dagger", 20, 40, .7, .2);
		} else if (name.equals("Claws")) {
			newWeapon = new Weapon("Claws", 10, 35, .65, .1);
		} else if (name.equals("Fangs")) {
			newWeapon = new Weapon("Fangs", 30, 60, .7, .2);
		} else if (name.equals("Scythe")) {
			newWeapon = new Weapon("Scythe", 40, 70, .7, .3);
		} else {
			newWeapon = new Weapon(name, 100, 200, 1, .5);
		}
		return newWeapon;
	}

}
