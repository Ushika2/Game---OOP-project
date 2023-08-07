package monster;

public class Dragon extends Monster{
    public int attackDamage;
    public Dragon(String name, int HP){
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
