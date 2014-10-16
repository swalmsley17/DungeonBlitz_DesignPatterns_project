package Character;

import AnimatingImage.Animation;

public class Enemy extends Character {

	public Enemy(String name, int maxHealth) {
		super(maxHealth);
		this.name = name;
	}

	private String currentAnimation = "standing";
	
	protected EnemyAttributes attributes;

	public EnemyAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(EnemyAttributes attributes) {
		this.attributes = attributes;
	}
	
	public void setAnimation(String currentAnimation){
		this.currentAnimation = currentAnimation;		
	}
	
	public Animation activeAnimation(){
		
		if(currentAnimation.equals("walking")){
			return this.getWalking();
		}
		else if(currentAnimation.equals("fighting")){
			return this.getFighting();
		}
		else if(currentAnimation.equals("gotHit")){
			return this.getGotHit();
		}
		else{
			return this.getStanding();
		}
	}
	
	
	@Override
	public String toString() {
		return "Enemy [attributes=" + attributes + ", name=" + name
				+ ", health=" + health + ", maxHealth=" + maxHealth
				+ ", weapon=" + weapon + ", point=" + this.getPoint() + ", velocity="
				+ velocity + ", imagePath=" + spriteImg + ", image= + will be added" 
				+ "]\n";
	}

}
