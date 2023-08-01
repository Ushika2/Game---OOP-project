package spell;

// public class Fire extends Spell{
//     public Fire(int damage){
//         super(damage);
//     }
// }

public class Fire implements Spell{   // implements from spells
    private String name;
    private final int damage;

    public Fire(String name, int damage){  //constructor
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