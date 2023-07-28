package monster;

// Monster
public abstract class Monster{
    private String name;
    private int HP; //healthpoints

    public Monster(String name, int HP){
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

    public abstract void attack(Monster taget);
    public abstract void takeDamage(int damage);
}