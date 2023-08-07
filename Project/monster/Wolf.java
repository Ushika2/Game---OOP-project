package monster;

public class Wolf extends Monster{
    public int attackDamage;
    public Wolf(String name, int HP){
        super(name, HP);
    }

    public int getDamage(int damage){
        return attackDamage;
    }

    public void takeDamage(int damage){
        setHP(getHP() - damage);
    }

    // public void attack(Monster target){
    //     target.takeDamage(attackDamage);
    // }
}
