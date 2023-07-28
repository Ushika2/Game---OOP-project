package monster;

public class Orgre extends Monster{
    public int attackDamage;
    public Orgre(String name, int HP){
        super(name, HP);
    }

    public int getDamage(int damage){
        return attackDamage;
    }

    public void takeDamage(int damage){
        setHP(getHP() - damage);
    }

    public void attack(Monster target){
        target.takeDamage(attackDamage);
    }
}
