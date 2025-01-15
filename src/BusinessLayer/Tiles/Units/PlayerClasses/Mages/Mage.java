package BusinessLayer.Tiles.Units.PlayerClasses.Mages;

import java.util.List;
import BusinessLayer.Tiles.Units.Unit;
import BusinessLayer.Tiles.Units.EnemyClasses.Enemy;
import BusinessLayer.Tiles.Units.PlayerClasses.Player;

public class Mage extends Player 
{
    private int manaPool;
    private int currentMana;
    private int manaCost;
    private int spellPower;
    private int hitCounts;
    private int abilityRange;
    private int hits;


    public Mage(char tile, String name, int healthAmount, int attackPoints, int defensePoints, int manaPool, int manaCost, int spellPower, int hitCounts, int abilityRange) {
        super(tile, name, healthAmount, attackPoints, defensePoints);
        this.manaPool = manaPool;
        this.currentMana = (int)(manaPool/4);
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitCounts = hitCounts;
        this.abilityRange = abilityRange;
        this.hits = 0;
    }

    public int getManaPool() {
        return manaPool;
    }

    public void setManaPool(int manaPool) {
        this.manaPool = manaPool;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public int getSpellPower() {
        return spellPower;
    }

    public void setSpellPower(int spellPower) {
        this.spellPower = spellPower;
    }

    public int getHitCounts() {
        return hitCounts;
    }

    public int getAbilityRange() {
        return abilityRange;
    }

    public void setAbilityRange(int range) {
        this.abilityRange = range;
    }

    public void onGameTick() //regenerate mana
    {
        currentMana = Math.min(currentMana + (playerLevel * 1), manaPool);
    }

    public void levelUp()
    {
        super.levelUp(0,0,0);
        manaPool += 25 * playerLevel;
        currentMana = Math.min(currentMana + (int)(manaPool/4) , manaPool);
        spellPower += 10 * playerLevel;
    }

    @Override
    public boolean areEnoughResources() 
    {
        return currentMana >= manaCost;
    }

    @Override
    public String printStats()
    {
        String prev = super.printStats();
        return prev + ", Mana: " + currentMana + "/" + manaPool + ", Spell Power: " + spellPower;
    }

    @Override
    public void onAbilityCast() 
    {
        List<Enemy> enemies = gameContext.getEnemies();
        List<Enemy> filteredEnemies = filterEnemiesByDistance(enemies, abilityRange);
        currentMana = currentMana - manaCost;
        hits = 0;
        
        if(filteredEnemies.isEmpty()) //no enemies in range
        {
            return;
        }

        while(hits < hitCounts && !filteredEnemies.isEmpty()) //while there are still hits to make and enemies to hit
        {
            int randomEnemy = chooseRandomEnemy(filteredEnemies.size());
            Unit enemy = filteredEnemies.get(randomEnemy);
            enemy.getHealth().decreaseHealth(spellPower);
            hits++;
            if(!enemy.alive())
            {
                filteredEnemies.remove(randomEnemy);
            }
        }
    }
}