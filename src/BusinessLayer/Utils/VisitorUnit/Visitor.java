package BusinessLayer.Utils.VisitorUnit;

import BusinessLayer.Tiles.Empty;
import BusinessLayer.Tiles.Units.EnemyClasses.Enemy;
import BusinessLayer.Tiles.Units.PlayerClasses.Player;
import BusinessLayer.Tiles.Wall;

public interface Visitor {
    void visit(Player player);
    void visit(Enemy enemy);
    void visit(Wall wall);
    void visit(Empty empty);
}
