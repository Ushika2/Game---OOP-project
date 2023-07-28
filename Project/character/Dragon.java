package character;

public class Dragon extends Character{
    public int attackDamage;
    public Dragon(String name, int HP){
        super(name, HP);
    }

    public int getDamage(int damage){
        return attackDamage;
    }

    public void takeDamage(int damage){
        setHP(getHP() - damage);
        //System.out.println(getName() + "takes" + damage + "damage.");
    }

    public void attack(Character target){
        target.takeDamage(attackDamage);
        //System.out.println(getName() + "attacks" + target.getName() + "causing" + attackDamage + "damage.");
    }
}