package monster;

// sub-class
public class Goblin extends Monster{ // class definition (inheritance from the monster class)
    public int attackDamage;

    public Goblin(String name, int HP){  // constructor initializes the Goblin object with name and HP
        super(name, HP);  // call constructor of super-class 'monster' and set common properties
    }

    public int getDamage(int damage){  // getter method which returns damage done by goblin
        return attackDamage;
    }

    public void takeDamage(int damage){  // this method reduces the Hp of goblin by damage dealt
        setHP(getHP() - damage);
    }

}
