package BusinessLayer.Control;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import BusinessLayer.Tiles.Empty;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Wall;
import BusinessLayer.Tiles.Units.EnemyClasses.Enemy;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.BearWright;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.GiantWright;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.LannisterKnight;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.LannisterSoldier;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.NightsKing;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.QueenCersei;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.QueensGuard;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.TheMountain;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.WhiteWalker;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.Wright;
import BusinessLayer.Tiles.Units.EnemyClasses.Traps.TrapTypes.BonusTrap;
import BusinessLayer.Tiles.Units.EnemyClasses.Traps.TrapTypes.DeathTrap;
import BusinessLayer.Tiles.Units.EnemyClasses.Traps.TrapTypes.QueensTrap;
import BusinessLayer.Tiles.Units.PlayerClasses.Player;
import BusinessLayer.Tiles.Units.PlayerClasses.Mages.MageTypes.Melisandre;
import BusinessLayer.Tiles.Units.PlayerClasses.Mages.MageTypes.ThorosOfMyr;
import BusinessLayer.Tiles.Units.PlayerClasses.Rogues.RogueTypes.AryaStark;
import BusinessLayer.Tiles.Units.PlayerClasses.Rogues.RogueTypes.Bronn;
import BusinessLayer.Tiles.Units.PlayerClasses.Warriors.WarriorTypes.JohnSnow;
import BusinessLayer.Tiles.Units.PlayerClasses.Warriors.WarriorTypes.TheHound;
import BusinessLayer.Utils.Position;
import BusinessLayer.Utils.Generators.Generator;
import BusinessLayer.Utils.callbacks.DeathCallback;
import BusinessLayer.Utils.callbacks.MessageCallback;

public class TileFactory {
    private Player p;
    private static final List<Supplier<Player>> playerTypes = Arrays.asList(
            () -> new JohnSnow(),
            () -> new TheHound(),
            () -> new Melisandre(),
            () -> new ThorosOfMyr(),
            () -> new AryaStark(),
            () -> new Bronn()
    ); // List of player types

    private static final Map<Character, Supplier<Enemy>> enemyTypes = new HashMap<>();

    static {
        enemyTypes.put('s', () -> new LannisterSoldier());
        enemyTypes.put('k', () -> new LannisterKnight());
        enemyTypes.put('q', () -> new QueensGuard());
        enemyTypes.put('z', () -> new Wright());
        enemyTypes.put('b', () -> new BearWright());
        enemyTypes.put('g', () -> new GiantWright());
        enemyTypes.put('w', () -> new WhiteWalker());
        enemyTypes.put('M', () -> new TheMountain());
        enemyTypes.put('C', () -> new QueenCersei());
        enemyTypes.put('K', () -> new NightsKing());
        enemyTypes.put('B', () -> new BonusTrap());
        enemyTypes.put('Q', () -> new QueensTrap());
        enemyTypes.put('D', () -> new DeathTrap());
    } // Map of enemy types

    public Player producePlayer(int playerID) // Produces a player based on the player ID
    {
        if(playerID < 1 || playerID > playerTypes.size())
            throw new IllegalArgumentException("Invalid player ID");
        Supplier<Player> supp = playerTypes.get(playerID-1);
        this.p = supp.get(); // Get the player based on the player ID
        return this.p;
    }

    public Player producePlayer(){
        return this.p;
    }

    public Enemy produceEnemy(char tile, Position p, Generator g, MessageCallback m) // Produces an enemy based on the tile
    {
        if(!enemyTypes.containsKey(tile))
        {
            throw new IllegalArgumentException("Invalid enemy type");
        }
        Enemy e = enemyTypes.get(tile).get();
        DeathCallback dc = () ->{ e.die();};
        e.initialize(p, g, dc, m); // Initialize the enemy with the given position, generator, death callback and message callback
        return e;
    }

    public Tile produceEmpty(Position p) // Produces an empty tile
    {
        Empty e = new Empty();
        e.initialize(p);
        return e;
    }

    public Tile produceWall(Position p) // Produces a wall tile
    {
        Wall w = new Wall();
        w.initialize(p);
        return w;
    }
}
