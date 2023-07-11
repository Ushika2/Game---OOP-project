package spell;

// public class Fire extends Spell{
//     public Fire(int damage){
//         super(damage);
//     }
// }

public class Fire implements Spell{
    private String name;
    private final int damage;

    public Fire(String name, int damage){
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