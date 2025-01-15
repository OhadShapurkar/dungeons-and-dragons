package BusinessLayer.Tiles.Units.PlayerClasses.Warriors;

import java.util.List;
import BusinessLayer.Tiles.Units.EnemyClasses.Enemy;
import BusinessLayer.Tiles.Units.PlayerClasses.Player;

public class Warrior extends Player {
    private final int DISTANCE = 3;
    private int abilityCooldown;
    private int remainingCooldown;
    public Warrior(char tile, String name, int healthAmount, int attackPoints, int defensePoints, int abilityCooldown) {
        super(tile, name, healthAmount, attackPoints, defensePoints);
        this.abilityCooldown = abilityCooldown;
        this.remainingCooldown = 0;
    }
    public int getAbilityCooldown() {
        return abilityCooldown;
    }
    public void setAbilityCooldown(int abilityCooldown) {
        this.abilityCooldown = abilityCooldown;
    }
    public int getRemainingCooldown() {
        return remainingCooldown;
    }
    public void setRemainingCooldown(int remainingCooldown) {
        this.remainingCooldown = remainingCooldown;
    }

    public void levelUp()
    {
        super.levelUp(5 * (playerLevel+1), 2 * (playerLevel+1), 1 * (playerLevel+1));
        remainingCooldown = 0;
    }
    @Override
    public boolean areEnoughResources() {
        if(remainingCooldown > 0)
        {
            messageCallback.send("Ability is on cooldown: please wait " + remainingCooldown + " turns");
            return false;
        }
        return true;
    }
    
    public void onGameTick()
    {
        if(remainingCooldown > 0)
        {
            remainingCooldown--;
        }
    }


    @Override
    public String printStats()
    {
        String prev = super.printStats();
        return prev + ", Cooldown: " + remainingCooldown + "/" + abilityCooldown;
    }


    public void onAbilityCast()
    {
        if(!areEnoughResources())
        {
            return;
        }
        this.health.increaseHealth(10 * this.defense);
        messageCallback.send(this.name + " used ability: healed for " + (10 * this.defense) + " health");
        remainingCooldown = abilityCooldown + 1;
        List<Enemy> enemies = gameContext.getEnemies();
        List<Enemy> filteredEnemies = filterEnemiesByDistance(enemies, DISTANCE);
        
        if(filteredEnemies.isEmpty())
        {
            return;
        }
    
        int randomEnemy = chooseRandomEnemy(filteredEnemies.size());
        Enemy enemy = filteredEnemies.get(randomEnemy);
        int defense= enemy.defense();

        messageCallback.send(this.name + " used ability on " + enemy.getName() + " for " + (attack) + " damage");
        messageCallback.send(enemy.getName() + " defended for " + defense + " defense");
        enemy.getHealth().decreaseHealth(attack - defense);

        
    }
    

}
