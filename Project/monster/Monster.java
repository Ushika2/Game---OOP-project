package monster;

// Monster
public abstract class Monster{  //superclass
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

   // public abstract void attack(Monster taget);  // abstract methods
    public abstract void takeDamage(int damage);
}
