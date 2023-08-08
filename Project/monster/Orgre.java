package monster;

// sub-class
public class Orgre extends Monster{ // class definition (inheritance from the monster class)
    public int attackDamage;
    
    public Orgre(String name, int HP){  // constructor initializes the Orgre object with name and HP
        super(name, HP);  // call constructor of super-class 'monster' and set common properties
    }

    public int getDamage(int damage){  // getter method which returns damage done by orgre
        return attackDamage;
    }

    public void takeDamage(int damage){  // this method reduces the Hp of orgre by damage dealt
        setHP(getHP() - damage);
    }

}
