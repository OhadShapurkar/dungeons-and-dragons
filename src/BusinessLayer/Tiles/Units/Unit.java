package BusinessLayer.Tiles.Units;
import BusinessLayer.Game.GameContext;
import BusinessLayer.Tiles.Empty;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Units.EnemyClasses.Enemy;
import BusinessLayer.Tiles.Units.PlayerClasses.Player;
import BusinessLayer.Tiles.Wall;
import BusinessLayer.Utils.Health;
import BusinessLayer.Utils.Position;
import BusinessLayer.Utils.Generators.Generator;
import BusinessLayer.Utils.VisitorUnit.Visited;
import BusinessLayer.Utils.VisitorUnit.Visitor;
import BusinessLayer.Utils.callbacks.DeathCallback;
import BusinessLayer.Utils.callbacks.MessageCallback;

public abstract class Unit extends Tile {
    protected Health health;
    protected String name;
    protected int attack;
    protected int defense;
    protected Generator generator;
    protected DeathCallback deathCallback;
    protected MessageCallback messageCallback;
    protected GameContext gameContext;




    public Unit(char tile, String name, int healthAmount, int attackPoints, int defensePoints) {
        super(tile);
        this.name = name;
        this.health = new Health(healthAmount);
        this.attack = attackPoints;
        this.defense = defensePoints;
    }
    public boolean alive()
    {
        return health.alive();
    }

    public Unit initialize(Position p, Generator generator, DeathCallback deathCallback, MessageCallback messageCallback)
    {
        super.initialize(p);
        this.generator = generator;
        this.deathCallback = deathCallback;
        this.messageCallback = messageCallback;
        return this;
    }

    public int attack(){
        return this.generator.generate(attack);
    }
    public int defense(){
        return generator.generate(defense);
    }

    public void battle(Unit enemy){
        messageCallback.send(this.name + " engaged in combat with " + enemy.name + ".");
        messageCallback.send(this.printStats() + "\n" + enemy.printStats());
        int attack= this.attack();
        messageCallback.send(this.name + " Rolled " + attack + " attack points.");
        int defense= enemy.defense();
        messageCallback.send(enemy.name + " Rolled " + defense + " defense points.");
        int damage = Math.max(attack-defense,0);
        messageCallback.send(this.name + " Dealt " + damage +  " damage to " + enemy.name + ".");
        if(damage > 0) enemy.health.decreaseHealth(damage);
    }
    public void setGameContext(GameContext gameContext)
    {
        this.gameContext = gameContext;
    }

    public MessageCallback getMessageCallback() {
        return messageCallback;
    }


    public void interact(Tile tile) //double dispatch
    {
        tile.accept(this);
    }

    @Override
    public void visit(Empty empty) //move to empty tile
    {
        swapPosition(empty);
    }

    @Override
    public void visit(Wall wall)
    {
    }

    public String printStats(){
        return this.name + "  Health: " + health.getHealth() + "/" + health.getMaxHealth() + ", Attack: " + attack + ", Defense: " + defense;
    }

    public void onDeath(){
        deathCallback.onDeath();
    }
    public Health getHealth() {
        return health;
    }
    public String getName() {
        return name;
    }
    public int getAttack() {
        return attack;
    }
    public int getDefense() {
        return defense;
    }

    

}
