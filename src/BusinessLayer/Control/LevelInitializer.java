package BusinessLayer.Control;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import BusinessLayer.Utils.Generators.Generator;
import BusinessLayer.Utils.Position;
import BusinessLayer.Utils.callbacks.MessageCallback;
import BusinessLayer.Game.Board;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Units.PlayerClasses.Player;
import BusinessLayer.Tiles.Units.EnemyClasses.Enemy;

public class LevelInitializer {
    private Player player;
    private TileFactory tileFactory;
    private MessageCallback messageCallback;
    private Generator generator;
    public Position playerPosition;

    public LevelInitializer(Player player, MessageCallback messageCallback, Generator gen){
        this.player = player;
        this.tileFactory = new TileFactory();
        this.messageCallback = messageCallback;
        this.generator = gen;
    }
    public Board initLevel(String levelPath)
    {
        int Y = 0;
        int X = 0;
        List<String> lines;
        List<Tile> tiles = new ArrayList<>();
        List<Enemy> enemies = new ArrayList<>();

        try {
            lines = Files.readAllLines(Paths.get(levelPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(String line : lines){
            X = 0;
            for(char c : line.toCharArray()) //getting each character in the line
            {     
                switch(c) {
                    case '.': //empty tile
                        tiles.add(tileFactory.produceEmpty(new Position(X, Y)));
                        break;
                    case '#': //wall tile
                        tiles.add(tileFactory.produceWall(new Position(X, Y)));
                        break;
                    case '@': //player tile
                        this.playerPosition = new Position(X, Y);
                        this.player.setPosition(playerPosition);
                        tiles.add(this.player);
                        break;
                    default: //enemy tile
                        Enemy e = tileFactory.produceEnemy(c, new Position(X, Y), generator,
                        message -> {
                            //messageCallback.send("Enemy's message: " + message);
                            messageCallback.send(message);
                        });
                        tiles.add(e); 
                        enemies.add(e); //add enemy to the list of enemies
                        break;
                }
                X++;
            }
            Y++;
        }
        return new Board(tiles, player, enemies, X);

    }

    public Position getPlayerPosition() {
        return playerPosition;
    }
}
