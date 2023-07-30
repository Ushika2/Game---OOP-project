package character;
import spell.*;
import weapon.*;
import monster.Monster;

public class Mage extends Character{
    private Spell spell;
    private Weapon weapon;

    public Mage(String name, int HP, Spell spell, Weapon weapon){
        super(name, HP);
        this.spell = spell;
        this.weapon = weapon;
    }

    public Spell getSpell(){
        return spell;
    }

    public void setSpell(Spell spell){
        this.spell = spell;
    }

    public Weapon getWeapon(){
        return weapon;
    }

    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    public void takeDamage(int damage){
        setHP(getHP() - damage);
        System.out.println(getName() + "takes" + damage + "damage.");
    }

    public void attack(Monster target){
        int totalDamage = spell.getDamage() + weapon.getDamage();
        target.takeDamage(totalDamage);
        System.out.println(getName() + "cast spell on" + target.getName() + "causing" + totalDamage + "damage.");
    }

    public int damage(){
        int damage = weapon.getDamage();
        return damage;
    }
}
