package character;
import weapon.*;

// sub-class
public class Warrior extends Character { // class definition (inheritance from the character class)
    public static Object player;  //static field name
    private Weapon leftHandWeapon;
    private Weapon rightHandWeapon;

    public Warrior(String name, int HP, Weapon leftHandWeapon, Weapon rightHandWeapon){ // constructor initializes the Warrior object with name, HP and weapon for both hands
        super(name, HP); // call constructor of super-class 'character' and set common properties
        this.leftHandWeapon = leftHandWeapon;  // set other properties for warrior
        this.rightHandWeapon = rightHandWeapon;
    }

    public Weapon getLeftHandWeapon(){   // getter method which returns the left-hand weapon of warrior
        return leftHandWeapon;
    }

    public Weapon getRightHandWeapon(){  // getter method which returns the right-hand weapon of warrior
        return rightHandWeapon;
    }

    public void setLeftHandWeapon(Weapon leftHandWeapon){  // getter method which assign weapon to the left-hand of warrior
        this.leftHandWeapon = leftHandWeapon;
    }

    public void setRightHandWeapon(Weapon rightHandWeapon){  // setter method which assign weapon to the right-hand of warrior
        this.rightHandWeapon = rightHandWeapon;
    }

    public void takeDamage(int damage){  // this method reduces the Hp of warrior by damage dealt
        setHP(getHP() - damage);
    }

    public int damage(){  // this method returns the damage done by the warrior's weapons
        int damage = 0;
        if(getRightHandWeapon() == null){  //if warrior has only 1 weapon
            damage = leftHandWeapon.getDamage();   
        }
        else{
            damage = leftHandWeapon.getDamage() + rightHandWeapon.getDamage();   //if warrior has 2 weapons
        }
        return damage;
    }

}
