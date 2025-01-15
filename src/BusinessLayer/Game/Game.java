package BusinessLayer.Game;
import java.util.ArrayList;
import java.util.List;
import BusinessLayer.Control.LevelInitializer;
import BusinessLayer.Control.TileFactory;
import BusinessLayer.Tiles.Units.PlayerClasses.Player;
import BusinessLayer.Utils.Generators.Generator;
import BusinessLayer.Utils.callbacks.InputCallback;
import BusinessLayer.Utils.callbacks.MessageCallback;

public class Game 
{
    private List<MessageCallback> observers;
    private List<Level> levels;
    private int currentLevel;
    private Player player;
    private LevelInitializer levelInitializer;
    private InputCallback inputCallback;
    Generator generator;



    public Game(int playerID, Generator gen, InputCallback inputCallback)
    {
        this.currentLevel = 0;
        this.levels = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.generator = gen;
        this.inputCallback = inputCallback;
        TileFactory tileFactory = new TileFactory();
        this.player = tileFactory.producePlayer(playerID);
        this.player.initialize(null, generator, () -> {System.out.println("player died");}, message -> {
            //System.out.println("player: " + message);
            System.out.println(message);
        });
    }

    public void addObserver(MessageCallback observer)
    {
        observers.add(observer);
    }
    public void removeObserver(MessageCallback observer)
    {
        observers.remove(observer);
    }
    public void notifyObservers(String message)
    {
        for(MessageCallback observer : observers)
        {
            observer.send(message);
        }
    }

    public void initGame(String[] levelsPaths)
    {
        levelInitializer = new LevelInitializer(player, this::notifyObservers, generator);
        int counter = 1;
        for(String path : levelsPaths)
        {
            levels.add(new Level(levelInitializer.initLevel(path), counter, this::notifyObservers, levelInitializer.getPlayerPosition(), inputCallback)); // Initialize the levels
            counter++;
        }
    }


    public void startGame()
    {
        while(currentLevel <= levels.size()) 
        {
            this.player.setPosition(levels.get(currentLevel).getPlayerPosition()); // Set the player's position based on the current level
            int result = levels.get(currentLevel).startLevel(); // Start the level and get the result (1 if the player won, 0 if the player lost)
            if(result == 1)
            {
                currentLevel++;
                if(currentLevel == levels.size())
                {
                    this.player.getMessageCallback().send("You won!");
                    break;
                }
            }
            else
            {
                break;
            }
        }
    }

    public void startGameSys() // for tests
    {
        levels.get(currentLevel).startLevelSys();
        this.player.setPosition(levels.get(currentLevel).getPlayerPosition());
    }

    public void sysAction(String action) // for tests
    {
        if(levels.get(currentLevel).startLevelSysTick(action))
        {
            currentLevel++;
            if(currentLevel == levels.size())
            {
                this.player.getMessageCallback().send("You won!");
                return;
            }
            else
            {
                this.player.setPosition(levels.get(currentLevel).getPlayerPosition());
                levels.get(currentLevel).startLevelSys();
            }
        }
    }

    public Level getCurrentLevel()
    {
        return levels.get(currentLevel);
    }
}
