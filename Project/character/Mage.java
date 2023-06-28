package character;
import spell.*;

public class Mage extends Character{
    private Spell spell;

    public Mage(String name, int HP, Spell spell){
        super(name, HP);
        this.spell = spell;
    }

    public Spell getSpell(){
        return spell;
    }

    public void setSpell(Spell spell){
        this.spell = spell;
    }

    public void takeDamage(int damage){
        setHP(getHP() - damage);
        System.out.println(getName() + "takes" + damage + "damage.");
    }

    public void attack(Character target){
        int totalDamage = spell.getDamage();
        target.takeDamage(totalDamage);
        System.out.println(getName() + "cast spell on" + target.getName() + "causing" + totalDamage + "damage.");
    }
}
