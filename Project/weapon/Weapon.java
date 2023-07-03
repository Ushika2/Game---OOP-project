package weapon;

// Weapon
public class Weapon{
    private final int damage;
    private final String name;

    public Weapon(String name, int damage){
        this.name = name;
        this.damage = damage;
    }

    public int getDamage(){
        return damage;
    }

    public String getName(){
        return name;
    }
}

// Different weapons available:
// Sword
// Bow & Arrow
// Knife - at the beginning
// Magic book