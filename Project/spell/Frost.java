package spell;

// public class Frost extends Spell{
//     public Frost(int damage){
//         super(damage);
//     }
// }

public class Frost implements Spell{  // implements from spells
    private String name;
    private final int damage;

    public Frost(String name, int damage){  //constructor
        this.name = name;
        this.damage = damage;
    }
    public String getName(){  //returns the name of the spell
        return name;
    }
    public int getDamage(){  //returns the damage value associated with the spell.
        return damage;
    }
}