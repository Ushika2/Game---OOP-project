package character;
import weapon.*;

// sub-class
public class Healer extends Character{  // class definition (inheritance from the character class)
    private int healingPower;  //ability to heal
    private Weapon weapon;

    public Healer(String name, int HP, int healingPower, Weapon weapon){  // constructor initializes the Healer object with name, HP, healing power, and weapon
        super(name, HP);  // call constructor of super-class 'character' and set common properties
        this.healingPower = healingPower;   // set other properties for healer
        this.weapon = weapon;
    }

    public int getHealingPower(){  // getter method which return amount of healing power
        return healingPower;
    }

    public void setHealingPower(int healingPower){  // setter method to assign an amount to healing power
        this.healingPower = healingPower;
    }

    public Weapon getWeapon(){  // getter method which returns weapon of healer
        return weapon;
    }

    public void setWeapon(Weapon weapon){  // setter method which assign a weapon to the healer
        this.weapon = weapon;
    }

    public void takeDamage(int damage){  // this method reduces the Hp of healer by damage dealt
        setHP(getHP() - damage);
    }

    // Encapsulation - healer has its own method to heal 
    public void heal(Character target){  
        target.setHP(target.getHP() + healingPower);  // increases the Hp of target by the healing amount and updates it
    }

    public int damage(){  // this method returns the damage done by the healer's weapon
        int damage = weapon.getDamage();
        return damage;
    }
}
