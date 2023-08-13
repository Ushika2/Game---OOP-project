package spell;

public class Blast implements Spell{  // implements from spells
    private String name;
    private final int damage;

    public Blast(String name, int damage){  // constructor initializing object with name and damage
        this.name = name;
        this.damage = damage;
    }

    @Override //verifies the method is properly overriding the method from the interface. 
    public String getName(){  //returns the name of the spell
        return name;
    }
    @Override
    public int getDamage(){  //returns the damage value associated with the spell.
        return damage;
    }
}