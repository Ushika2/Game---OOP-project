package character;
import spell.*;
import weapon.*;

// sub-class
public class Mage extends Character{  // class definition (inheritance from the character class)
    private Spell spell;  // spell used by mage
    private Weapon weapon;

    public Mage(String name, int HP, Spell spell, Weapon weapon){ // constructor initializes the Mage object with name, HP, spell and weapon
        super(name, HP); // call constructor of super-class 'character' and set common properties
        this.spell = spell;  // set other properties for mage
        this.weapon = weapon;
    }

    public Spell getSpell(){  // getter method which returns spell of mage
        return spell;
    }

    public void setSpell(Spell spell){   // setter method which set a spell to mage
        this.spell = spell;
    }

    public Weapon getWeapon(){  // getter method which returns weapon of healer
        return weapon;
    }

    public void setWeapon(Weapon weapon){   // setter method which assign a weapon to the mage
        this.weapon = weapon;
    }

    public void takeDamage(int damage){  // this method reduces the Hp of mage by damage dealt
        setHP(getHP() - damage);
    }

    public int damage(){  // this method returns the damage done by the mage's weapon
        int damage = weapon.getDamage();
        return damage;
    }
}
