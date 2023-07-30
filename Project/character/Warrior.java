package character;
import weapon.*;
import monster.Monster;

public class Warrior extends Character {
    public static Object player;
    private Weapon leftHandWeapon;
    private Weapon rightHandWeapon;

    public Warrior(String name, int HP, Weapon leftHandWeapon, Weapon rightHandWeapon) {
        super(name, HP);
        this.leftHandWeapon = leftHandWeapon;
        this.rightHandWeapon = rightHandWeapon;
    }

    public Weapon getLeftHandWeapon() {
        return leftHandWeapon;
    }

    public Weapon getRightHandWeapon() {
        return rightHandWeapon;
    }

    public void setLeftHandWeapon(Weapon leftHandWeapon) {
        this.leftHandWeapon = leftHandWeapon;
    }

    public void setRightHandWeapon(Weapon rightHandWeapon) {
        this.rightHandWeapon = rightHandWeapon;
    }

    public void attack(Monster target) {
        int totalDamage = leftHandWeapon.getDamage() + rightHandWeapon.getDamage();
        target.takeDamage(totalDamage);
        //System.out.println(getName() + " attacks " + target.getName() + " for " + totalDamage + " damage.");
    }

    public void takeDamage(int damage) {
        setHP(getHP() - damage);
        //System.out.println(getName() + " takes " + damage + " damage.");
    }

    public int damage(){
        int damage = 0;
        if(getRightHandWeapon() == null){
            damage = leftHandWeapon.getDamage();
        }
        else{
            damage = leftHandWeapon.getDamage() + rightHandWeapon.getDamage();
        }
        return damage;
    }

}
