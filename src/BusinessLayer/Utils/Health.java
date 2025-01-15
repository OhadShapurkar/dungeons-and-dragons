package BusinessLayer.Utils;

public class Health {
    private int health;
    private int maxHealth;

    public Health(int health){
        this.health = health;
        this.maxHealth = health;
    }

    public int getHealth(){
        return health;
    }

    public int getMaxHealth(){
        return maxHealth;
    }

    public boolean alive(){
        return health > 0;
    }

    public void increaseHealth(int amount){
        if (amount < 0)
            return;
        health += amount;
        if(health > maxHealth)
            health = maxHealth;
    }

    public void decreaseHealth(int amount){
        if (amount < 0)
            return;
        health -= amount;
        if(health < 0)
            health = 0;
    }

    public void heal()
    {
        health = maxHealth;
    }

    public void increaseMaxHealth(int amount){
        if (amount < 0)
            return;
        maxHealth += amount;
    }
}
