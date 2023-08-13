package spell;

public class Fire implements Spell{   // implements from spells
    private String name;
    private final int damage;

    public Fire(String name, int damage){  // constructor initializing object with name and damage
        this.name = name;
        this.damage = damage;
    }
    @Override
    public String getName(){  //returns the name of the spell
        return name;
    }
    @Override
    public int getDamage(){  //returns the damage value associated with the spell.
        return damage;
    }
}