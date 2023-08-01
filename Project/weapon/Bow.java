package weapon;

public class Bow extends Weapon{  //inheritance from weapons
    public Bow(String name,int damage) {  //constructor
        super(name,damage);  // calls constructor of superclass to initialize properties inherited from weapon class
    }
}
