package weapon;

// Weapon
public class Weapon{  //superclass
    private final int damage;
    private final String name;

    public Weapon(String name, int damage){  //constructor
        this.name = name;  // assign parameter name to constructor name
        this.damage = damage;  // assign parameter damage to constructor damage
    }

    public int getDamage(){  // returns damage value of weapon
        return damage;
    }

    public String getName(){  //returns name of weapon
        return name;
    }
}

// Different weapons available:
// Sword
// Knife - at the beginning
// Grimoire