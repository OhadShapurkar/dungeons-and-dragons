package BusinessLayer.Tiles.Units.EnemyClasses.Traps.TrapTypes;

import BusinessLayer.Tiles.Units.EnemyClasses.Traps.Trap;

public class DeathTrap extends Trap {
    public DeathTrap() {

        super('D', "Death Trap", 500, 100, 20, 250, 1, 10);
    }
}
