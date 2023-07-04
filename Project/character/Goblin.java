package character;

public class Goblin extends Character{
    private int attackDamage = 5;
    public Goblin(String name, int HP){
        super(name, HP);
    }

    public void takeDamage(int damage){
        setHP(getHP() - damage);
        System.out.println(getName() + "takes" + damage + "damage.");
    }

    public void attack(Character target){
        target.takeDamage(attackDamage);
        System.out.println(getName() + "attacks" + target.getName() + "causing" + attackDamage + "damage.");
    }
}
