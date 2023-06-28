package character;

// Character 
public abstract class Character{
    private String name;
    private int HP; //healthpoints

    public Character(String name, int HP){
        this.name = name;
        this.HP = HP;
    }

    public String getName(){
        return name;
    }

    public int getHP(){
        return HP;
    }

    public void setHP(int HP){
        this.HP = HP;
    }

    public abstract void attack(Character taget);
    public abstract void takeDamage(int damage);
}

// Character:
// warrior
// Healer
// mage
// archer
