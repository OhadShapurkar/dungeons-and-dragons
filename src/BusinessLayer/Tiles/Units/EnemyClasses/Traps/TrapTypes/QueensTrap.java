package BusinessLayer.Tiles.Units.EnemyClasses.Traps.TrapTypes;

import BusinessLayer.Tiles.Units.EnemyClasses.Traps.Trap;

public class QueensTrap extends Trap {
    public QueensTrap() {
        super('Q', "Queen's Trap", 250, 50, 10, 100, 3, 7);
    }
}
