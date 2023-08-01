package weapon;

public class Axe extends Weapon{  //inheritance from weapons
    public Axe(String name,int damage) {  //constructor
        super(name,damage);  // calls constructor of superclass to initialize properties inherited from weapon class
    }
}