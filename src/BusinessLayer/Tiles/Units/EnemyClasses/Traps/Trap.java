package BusinessLayer.Tiles.Units.EnemyClasses.Traps;

import BusinessLayer.Tiles.Units.EnemyClasses.Enemy;

public class Trap extends Enemy {
    protected int visibleTime;
    protected int invisibleTime;
    protected int tickCount;
    protected boolean visible;

    public Trap(char tile, String name, int healthAmount, int attackPoints, int defensePoints, int experienceValue, int visibleTime, int invisibleTime) {
        super(tile, name, healthAmount, attackPoints, defensePoints, experienceValue);
        this.visibleTime = visibleTime;
        this.invisibleTime = invisibleTime;
        this.tickCount = 0;
        this.visible = true;
    }

    public int getVisibleTime() { return visibleTime; }

    public int getInvisibleTime() { return invisibleTime; }

    public int getTickCount() { return tickCount; }

    public void onTick() {
        if (tickCount == visibleTime + invisibleTime) {
            tickCount = 0;
            visible = !visible;
        }
        else{
            tickCount++;
        }
        if(tickCount == visibleTime){
            visible = false;
        }
        else if(tickCount == visibleTime + invisibleTime){
            visible = true;
        }
        if(this.getPosition().distance(this.gameContext.getPlayer().getPosition()) <= 2){
            this.interact(this.gameContext.getPlayer());
        }
    }

    public boolean isVisible() {
        return visible;
    }
    

    @Override
    public void onEnemyTurn() {
        onTick();
    }

    @Override
    public String toString() {
        return visible ? super.toString() : ".";
    }
    public String printStats() {
        String prev = super.printStats();
        return prev + ", Visible Time: " + visibleTime + ", Invisible Time: " + invisibleTime;
    }
}
