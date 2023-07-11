package spell;

// public class Lightning extends Spell{
//     public Lightning(int damage){
//         super(damage);
//     }
// }

public class Lightning implements Spell{
    private String name;
    private final int damage;

    public Lightning(String name, int damage){
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