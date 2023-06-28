package spell;

// Spell
public class Spell{
    private String name;
    private final int damage;

    public String getName(){
        return name;
    }

    public Spell(int damage){
        this.damage = damage;
    }

    public int getDamage(){
        return damage;
    }
}

// Spells:
// Fire 
// Lightning
// Frost
// Blast