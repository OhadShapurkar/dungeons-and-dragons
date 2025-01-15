package BusinessLayer.Game;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import BusinessLayer.Tiles.Empty;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Units.EnemyClasses.Enemy;
import BusinessLayer.Tiles.Units.PlayerClasses.Player;
import BusinessLayer.Utils.Position;

public class Board implements GameContext {

    private Map<Position, Tile> board;
    private Player player;
    private List<Enemy> enemies;
    private final int width;

    public Board(List<Tile> tiles, Player p, List<Enemy> enemies, int width){
        this.player = p;
        this.enemies = enemies;
        this.width = width;
        this.board = new TreeMap<>();
        for(Tile t : tiles){
            board.put(t.getPosition(), t); // Add the tile to the board
        }
        this.player.setGameContext(this); // Set the game context for the player
        for(Enemy e : enemies){
            e.setGameContext(this); // Set the game context for the enemies
        }
    }


    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder(); 
        for(Map.Entry<Position, Tile> entry : board.entrySet()) // Iterate over the board
        {
            sb.append(entry.getValue().toString());
            if(entry.getKey().getX() == width-1) // If we reached the end of the row
            {
                sb.append("\n");
            }

        }
        sb.append("\n");
        return sb.toString();
    }

    public void updateBoard() {
        // Create a list to store entries to be updated
        List<Map.Entry<Position, Tile>> entriesToUpdate = new ArrayList<>();

        // Iterate over the entries and collect the ones that need to be updated
        for (Map.Entry<Position, Tile> p : board.entrySet()) {
            if (!p.getKey().equals(p.getValue().getPosition())) {
                entriesToUpdate.add(p);
            }
        }
        
        // Create a list to store positions that will be updated
        List<Position> positionsToRemove = new ArrayList<>();
        List<Tile> tilesToAdd = new ArrayList<>();
        
        // Prepare for the updates
        for (Map.Entry<Position, Tile> entry : entriesToUpdate) {
            positionsToRemove.add(entry.getKey());
            tilesToAdd.add(entry.getValue());
        }
        
        // Perform the removal and addition separately
        for (Position pos : positionsToRemove) {
            board.remove(pos);
        }
        
        for (Tile tile : tilesToAdd) {
            board.put(tile.getPosition(), tile);
        }
    }

    public void removeEnemy(Enemy e) // Remove an enemy from the board and the list of enemies and replace it with an empty tile
    {
        Position p = e.getPosition();
        Empty empty = new Empty();
        board.put(p, empty);
        enemies.remove(e);
        empty.initialize(p);
    }

    public Tile getTile(Position p)
    {
        return board.get(p);
    }

    public Player getPlayer() 
    {
        return player;
    }

    public List<Enemy> getEnemies() 
    {
        return enemies;
    }

    public int getWidth() 
    {
        return width;
    }
    
}
