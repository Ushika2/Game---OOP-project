package spell;

// public class Frost extends Spell{
//     public Frost(int damage){
//         super(damage);
//     }
// }

public class Frost implements Spell{
    private String name;
    private final int damage;

    public Frost(String name, int damage){
        this.name = name;
        this.damage = damage;
    }
    public String getName(){
        return name;
    }
    public int getDamage(){
        return damage;
    }
}