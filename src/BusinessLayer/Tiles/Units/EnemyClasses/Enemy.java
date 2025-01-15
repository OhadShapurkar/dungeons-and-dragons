package BusinessLayer.Tiles.Units.EnemyClasses;

import BusinessLayer.Tiles.Units.PlayerClasses.Player;
import BusinessLayer.Tiles.Units.Unit;
import BusinessLayer.Utils.VisitorUnit.Visitor;

public abstract class Enemy extends Unit {
    protected int experienceValue;

    public Enemy(char tile, String name, int healthAmount, int attackPoints, int defensePoints, int experienceValue) {
        super(tile, name, healthAmount, attackPoints, defensePoints);
        this.experienceValue = experienceValue;
    }

    public String getName() {
        return name;
    }

    public int getExperienceValue() {
        return experienceValue;
    }

    public void setExperienceValue(int experienceValue) {
        this.experienceValue = experienceValue;
    }

    public abstract void onEnemyTurn();

    @Override
    public void accept(Visitor visitor) //double dispatch if unit is player, battle, if unit is enemy, do nothing
    {
        visitor.visit(this);
    }
    @Override
    public void visit(Enemy enemy) 
    {
        //Do nothing
    }
    @Override
    public void visit(Player player) //if enemy meets player, battle
    {
        battle(player);
        if(!player.alive()){
            kill(player);
        }
    }
    
    public void die()
    {
        gameContext.removeEnemy(this); //remove enemy from board
    }

    public void kill(Player player){
        player.onDeath();
    }

    @Override
    public String printStats() {
        String prev = super.printStats();
        return prev + ", Experience Value: " + experienceValue;
    }
}
