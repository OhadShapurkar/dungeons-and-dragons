package BusinessLayer.Tiles.Units.PlayerClasses;

import BusinessLayer.Tiles.Units.EnemyClasses.Enemy;
import BusinessLayer.Utils.Position;


import java.util.List;
import java.util.stream.Collectors;

import BusinessLayer.Tiles.Units.HeroicUnit;
import BusinessLayer.Tiles.Units.Unit;
import BusinessLayer.Utils.VisitorUnit.Visitor;

public abstract class Player extends Unit implements HeroicUnit {
    
    protected int experiencePoints;
    protected int playerLevel;

    public Player(char tile, String name, int healthAmount, int attackPoints, int defensePoints) 
    {
        super(tile, name, healthAmount, attackPoints, defensePoints);
        playerLevel = 1;
        experiencePoints = 0;
        // this.randomGenerator = new RandomGenerator();
        // this.playerMessageCallback = new PlayerMessageCallback();
        // this.playerDeathCallback = new PlayerDeathCallback(name);

    }


    public void addExperiencePoints(int experiencePoints)
    {
        this.experiencePoints += experiencePoints;
        while(shouldLevelUp())
        {
            levelUp();
        }
    }

    public abstract void levelUp();

    public abstract void onGameTick();

    public void addDefence(int defence)
    {
        this.defense += defence;
    }

    public boolean shouldLevelUp()
    {
        if(experiencePoints >= playerLevel*50)
        {
            return true;
        }
        return false;
    }

    protected void levelUp(int additionalHp, int additionalAp, int additionalDp)
    {
        int expectedXPforlvlUP = playerLevel*50;
        if (experiencePoints - expectedXPforlvlUP > 0)
            experiencePoints -= playerLevel*50;
        else experiencePoints = 0;
        playerLevel++;
        additionalHp += 10 * playerLevel;
        additionalAp += 4 * playerLevel;
        additionalDp += 1 * playerLevel;
        health.increaseMaxHealth(additionalHp);
        health.heal();
        attack += additionalAp;
        defense += additionalDp;
        messageCallback.send(name + " reached level " + playerLevel + ": +" + additionalHp + " Health, +" + additionalAp + " Attack, +" + additionalDp + " Defense");
    }


    public int chooseRandomEnemy(int enemiesCount)
    {
        return generator.generate(enemiesCount);
    }

    public abstract boolean areEnoughResources();

    public abstract void onAbilityCast();

    public void abilityCast()
    {
        if(areEnoughResources())
        {
            onAbilityCast();
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void visit(Player player){
        //Do nothing
    }
    @Override
    public void visit(Enemy enemy){
        battle(enemy);
        //enemy.printStats();
        if(!enemy.alive()){
            kill(enemy); //implement it Ohad
        }
    }

    public void kill(Enemy enemy)
    {
        Position p = enemy.getPosition();
        this.addExperiencePoints(enemy.getExperienceValue());
        messageCallback.send(enemy.getName() + " died. " + this.getName() + " gained " + enemy.getExperienceValue() + " experience");
        enemy.onDeath();
        gameContext.getTile(p).accept(this);
    }

    public List<Enemy> filterEnemiesByDistance(List<Enemy> enemies, int range) {
        Position playerPosition = this.getPosition();
        return enemies.stream()
                .filter(enemy -> playerPosition.distance(enemy.getPosition()) <= range)
                .collect(Collectors.toList());
    }
    
    public int getPlayerLevel()
    {
        return playerLevel;
    }

    public int getExperiencePoints()
    {
        return experiencePoints;
    }

    public int getCurrentNeededXP()
    {
        return playerLevel*50;
    }

    @Override
    public String toString() {
        return alive() ? "@" : "X";
    }

    @Override
    public String printStats() {
        String prev = super.printStats();
        return prev + ", Level: " + playerLevel + ", Experience: " + experiencePoints + "/" + (playerLevel*50);
    }

}
