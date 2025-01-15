package BusinessLayer.Tiles;
import BusinessLayer.Tiles.Units.Unit;
import BusinessLayer.Utils.Position;
import BusinessLayer.Utils.VisitorUnit.Visited;
import BusinessLayer.Utils.VisitorUnit.Visitor;

public abstract class Tile implements Visitor, Visited {
    protected Position position;
    protected char tile;

    public Tile(char tile){
        this.tile = tile;
    }

    public Tile(char Tile, Position position)
    {
        this.tile = Tile;
        this.position = position;
    }

    public char getTile(){
        return tile;
    }

    public void initialize(Position position){
        this.position = position;
    }
    public Position getPosition(){
        return position;
    }
    public void setPosition(Position other){
        this.position = other;
    }
    public void swapPosition (Tile tile){
        Position temp = this.position;
        this.position = tile.position;
        tile.position = temp;
    }


    @Override
    public String toString() {
        return String.valueOf(tile);
    }
}
