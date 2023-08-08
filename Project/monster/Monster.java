package monster;

// Monster superclass
public abstract class Monster{  // abstract class definition
    private String name;
    private int HP; //healthpoints

    public Monster(String name, int HP){  //constructor 
        this.name = name;  // assign parameter name to constructor name
        this.HP = HP;  // assign parameter Hp to constructor Hp
    }

    public String getName(){  // returns name of monster
        return name;
    }

    public int getHP(){  //returns Hp of monster
        return HP;
    }

    public void setHP(int HP){  //sets Hp value of monster
        this.HP = HP;
    }

    // abstract method
    public abstract void takeDamage(int damage);  //method where the monster receives damage
}
