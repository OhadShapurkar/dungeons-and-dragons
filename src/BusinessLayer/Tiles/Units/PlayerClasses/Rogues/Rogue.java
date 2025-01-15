package BusinessLayer.Tiles.Units.PlayerClasses.Rogues;

import java.util.List;

import BusinessLayer.Tiles.Units.Unit;
import BusinessLayer.Tiles.Units.EnemyClasses.Enemy;
import BusinessLayer.Tiles.Units.PlayerClasses.Player;

public class Rogue extends Player {
    private final int DISTANCE = 2;
    private int currentEnergy;
    private int maxEnergy;
    private int abilityCost;
    public Rogue(char tile, String name, int healthAmount, int attackPoints, int defensePoints, int maxEnergy, int abilityCost) 
    {
        super(tile, name, healthAmount, attackPoints, defensePoints);
        this.currentEnergy = maxEnergy;
        this.maxEnergy = maxEnergy;
        this.abilityCost = abilityCost;
    }
    public int getCurrentEnergy() {
        return currentEnergy;
    }
    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
    }
    public int getMaxEnergy() {
        return maxEnergy;
    }
    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }
    public int getAbilityCost() {
        return abilityCost;
    }
    public void setAbilityCost(int abilityCost) {
        this.abilityCost = abilityCost;
    }
    public void onGameTick()
    {
        this.currentEnergy = Math.min(this.currentEnergy + 10, this.maxEnergy);
    }

    public void levelUp()
    {
        super.levelUp(0,((playerLevel + 1)* 3),0);
        this.currentEnergy = this.maxEnergy;
    }

    @Override
    public void onAbilityCast()
    {
        this.currentEnergy -= this.abilityCost;
        List<Enemy> enemies = gameContext.getEnemies();
        List<Enemy> filteredEnemies = filterEnemiesByDistance(enemies, DISTANCE);
        if(filteredEnemies.isEmpty())//no enemies in range
        {
            return;
        }
        for(Unit enemy : filteredEnemies)//attack all enemies in range
        {
            enemy.getHealth().decreaseHealth(this.attack);
        }
    }

    public String printStats()
    {
        String prev = super.printStats();
        return prev + ", Energy: " + currentEnergy + "/" + maxEnergy;
    }

    @Override
    public boolean areEnoughResources() 
    {
        return(this.currentEnergy >= this.abilityCost);
    }
}
