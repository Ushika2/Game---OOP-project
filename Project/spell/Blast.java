package spell;

// public class Blast extends Spell{
//     public Blast(int damage){
//         super(damage);
//     }
// }

public class Blast implements Spell{
    private String name;
    private final int damage;

    public Blast(String name, int damage){
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