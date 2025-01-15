package BusinessLayer.Tiles.Units.EnemyClasses.Monsters;

import BusinessLayer.Tiles.Units.EnemyClasses.Enemy;
import BusinessLayer.Utils.Position;

public class Monster extends Enemy {
    protected int visionRange;

    public Monster(char tile, String name, int healthAmount, int attackPoints, int defensePoints, int experienceValue, int visionRange) {
        super(tile, name, healthAmount, attackPoints, defensePoints, experienceValue);
        this.visionRange = visionRange;
    }

    public int getVisionRange() {
        return visionRange;
    }

    public void chasePlayer() //chase player if in vision range
    {
        int dx = this.getPosition().getX() - this.gameContext.getPlayer().getPosition().getX();
        int dy = this.getPosition().getY() - this.gameContext.getPlayer().getPosition().getY();
        if(Math.abs(dx) > Math.abs(dy))
        {
            if(dx > 0)
            {
                this.interact(this.gameContext.getTile(new Position(this.getPosition().getX() - 1, this.getPosition().getY())));
            }
            else
            {
                this.interact(this.gameContext.getTile(new Position(this.getPosition().getX() + 1, this.getPosition().getY())));
            }
        }
        else
        {
            if(dy > 0)
            {
                this.interact(this.gameContext.getTile(new Position(this.getPosition().getX(), this.getPosition().getY() - 1)));
            }
            else
            {
                this.interact(this.gameContext.getTile(new Position(this.getPosition().getX(), this.getPosition().getY() + 1)));
            }
        }
    }

    public void Move() //move randomly
    {
        this.generator.generate(4);
        int direction = this.generator.generate(4);
        switch (direction) {
            case 0:
                this.interact(this.gameContext.getTile(new Position(this.getPosition().getX() + 1, this.getPosition().getY())));
                break;
            case 1:
                this.interact(this.gameContext.getTile(new Position(this.getPosition().getX() - 1, this.getPosition().getY())));
                break;
            case 2:
                this.interact(this.gameContext.getTile(new Position(this.getPosition().getX(), this.getPosition().getY() + 1)));
                break;
            case 3:
                this.interact(this.gameContext.getTile(new Position(this.getPosition().getX(), this.getPosition().getY() - 1)));
                break;
        }
    }

    @Override
    public void onEnemyTurn() 
    {
        if(this.health.getHealth() <= 0) //enemy is dead
        {
            this.deathCallback.onDeath();
        }
        else
        {
            if(this.gameContext.getPlayer().getPosition().distance(this.getPosition()) < visionRange) //player in vision range
            {
                chasePlayer();
            }
            else //player not in vision range
            {
                Move();
            }
        }
    }

    @Override
    public String printStats() {
        String prev = super.printStats();
        return prev + ", Vision Range: " + visionRange;
    }
}
