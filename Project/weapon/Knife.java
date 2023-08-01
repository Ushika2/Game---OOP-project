package weapon;

public class Knife extends Weapon{  //inheritance from weapons
    public Knife(String name,int damage) {  //constructor
        super(name,damage);  // calls constructor of superclass to initialize properties inherited from weapon class
    }
}
