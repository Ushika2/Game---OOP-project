package weapon;

public class Grimoire extends Weapon{  //inheritance from weapons
    public Grimoire(String name,int damage) {  //constructor
        super(name,damage);  // calls constructor of superclass to initialize properties inherited from weapon class
    }
}
