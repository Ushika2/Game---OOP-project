package character;
import weapon.*;

public class Archer extends Character{
    private Weapon weapon;

    public Archer(String name, int HP, Weapon weapon){
        super(name, HP);
        this.weapon = weapon;
    }

    public Weapon getWeapon(){
        return weapon;
    }

    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    public void attack(Character target){
        int totalDamage = weapon.getDamage();
        target.takeDamage(totalDamage);
        System.out.println(getName() + "attacks" + target.getName() + "for" + totalDamage + "damage.");
    }

    public void takeDamage(int damage){
        setHP(getHP() - damage);
        System.out.println(getName() + "takes" + damage + "damage.");
    }
}
