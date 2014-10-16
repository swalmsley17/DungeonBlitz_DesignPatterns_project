package Character;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import Weapon.Weapon;

import AnimatingImage.Animation;

public abstract class Character {

	protected String name;
	protected int health, maxHealth;

	protected Weapon weapon;

	
	protected Point point;
	protected double xPosition;
	protected double yPosition; 	
	protected int velocity;

	
	protected BufferedImage spriteImg;
	protected Animation walking;
	protected Animation fighting;
	protected Animation standing;
	protected Animation gotHit;
	
	
	protected void setSprite(String location){
        try {
        	URL spriteURL = this.getClass().getClassLoader().getResource(location);

			this.spriteImg = ImageIO.read(spriteURL);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	protected Character(int maxHealth) {
		this.health = maxHealth;
		this.maxHealth = maxHealth;
	}

	public void setWalking(Animation walk){
		walking = walk;
	}
	
	public Animation getWalking(){
		return walking;
	}
	
	public void setFighting(Animation fight){
		fighting = fight;
	}
	
	public Animation getFighting(){
		return fighting;
	}	

	public void setGotHit(Animation hit){
		gotHit = hit;
	}
	
	public Animation getGotHit(){
		return gotHit;
	}		
	
	
	public void setStanding(Animation stand){
		standing = stand;
	}
	
	public Animation getStanding(){
		return standing;
	}	
	
	public BufferedImage getSprite(){
		return spriteImg;
	}
	
	public String getName() {
		return this.name;
	}

	public int getHealth() {
		return this.health;
	}

	public boolean isAlive() {
		return this.health > 0;
	}

	public void takeDamage(int damage) {
		if (damage > this.health)
			this.health = 0;
		else
			this.health -= damage;
	}

	public boolean attack(Character enemy) {
		this.weapon.getDamage();
	
		if (this.isAlive() && this.weapon.attackLanded()) {
			int damageDealt = this.weapon.getDamage();
			enemy.takeDamage(damageDealt);

			return true;
		}	
		return false;
	}

	public double getX(){
		return this.xPosition;
	}
	
	
	public double getY(){
		return this.yPosition;
	}

	public void setX(double xPosition){
		this.xPosition = xPosition;
	}
	
	
	public void setY(double yPosition){
		this.yPosition = yPosition;
	}	
	
	public Point getPoint() {
		return point.getLocation();
	}

	public void setPoint(Point point) {
		this.point = point;
	}
	
	
	public void setPoint(int x, int y){
		this.point.x = x;
		this.point.y = y;
	}
	
	
	public void setPoint(double x, double y){
		this.xPosition = x;
		this.yPosition= y;
	}

	
	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}


	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
}
