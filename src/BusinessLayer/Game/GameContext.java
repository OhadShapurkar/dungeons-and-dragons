package BusinessLayer.Game;

import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Units.EnemyClasses.Enemy;
import BusinessLayer.Tiles.Units.PlayerClasses.Player;
import BusinessLayer.Utils.Position;

import java.util.List;

public interface GameContext //player and enemy should have access to certain methods in the board
{
    public List<Enemy> getEnemies();
    public Tile getTile(Position p);
    public Player getPlayer();
    public void removeEnemy(Enemy e);
}
