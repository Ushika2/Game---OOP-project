package monster;

// sub-class
public class Wraith extends Monster{ // class definition (inheritance from the monster class)
    public int attackDamage;

    public Wraith(String name, int HP){ // constructor initializes the Wraith object with name and HP
        super(name, HP);  // call constructor of super-class 'monster' and set common properties
    }

    public int getDamage(int damage){ // getter method which returns damage done by wraith
        return attackDamage;
    }

    public void takeDamage(int damage){  // this method reduces the Hp of wraith by damage dealt
        setHP(getHP() - damage);
    }

}
