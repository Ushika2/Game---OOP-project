package monster;

// sub-class
public class Wolf extends Monster{  // class definition (inheritance from the monster class)
    public int attackDamage;

    public Wolf(String name, int HP){  // constructor initializes the Wolf object with name and HP
        super(name, HP);  // call constructor of super-class 'monster' and set common properties
    }

    public int getDamage(int damage){  // getter method which returns damage done by wolf
        return attackDamage;
    }

    public void takeDamage(int damage){  // this method reduces the Hp of wolf by damage dealt
        setHP(getHP() - damage);
    }

}
