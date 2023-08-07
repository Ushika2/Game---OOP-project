package character;
import weapon.*;
import monster.Monster;

public class Healer extends Character{
    private int healingPower;
    private Weapon weapon;

    public Healer(String name, int HP, int healingPower, Weapon weapon){
        super(name, HP);
        this.healingPower = healingPower;
        this.weapon = weapon;
    }

    public int getHealingPower(){
        return healingPower;
    }

    public void setHealingPower(int healingPower){
        this.healingPower = healingPower;
    }

    public Weapon getWeapon(){
        return weapon;
    }

    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    // public void attack(Monster target){
    //     int totalDamage = weapon.getDamage();
    //     target.takeDamage(totalDamage);
    //     System.out.println(getName() + "attacks" + target.getName() + "for" + totalDamage + "damage.");
    // }

    public void takeDamage(int damage){
        setHP(getHP() - damage);
        System.out.println(getName() + "takes" + damage + "damage.");
    }

    public void heal(Character target) {
        target.setHP(target.getHP() + healingPower);
        System.out.println(getName() + " heals " + target.getName() + " for " + healingPower + " health points.");
    }

    public int damage(){
        int damage = weapon.getDamage();
        return damage;
    }
}
