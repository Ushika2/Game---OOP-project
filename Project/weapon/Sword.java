package weapon;

public class Sword extends Weapon{  //inheritance from weapon
    public Sword(String name, int damage) {  //constructor
        super(name,damage);  // calls constructor of superclass to initialize properties inherited from weapon class
    }
}
