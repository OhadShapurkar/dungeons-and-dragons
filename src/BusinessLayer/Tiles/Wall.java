package BusinessLayer.Tiles;

import BusinessLayer.Tiles.Units.EnemyClasses.Enemy;
import BusinessLayer.Tiles.Units.PlayerClasses.Player;
import BusinessLayer.Tiles.Units.Unit;
import BusinessLayer.Utils.VisitorUnit.Visitor;

public class Wall extends Tile
{
    public static final char tile = '#';
    public Wall(){
        super(tile);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void visit(Player player) {}
    @Override
    public void visit(Enemy enemy) {}
    @Override
    public void visit(Wall wall) {}
    @Override
    public void visit(Empty empty) {}
}
