package BusinessLayer.Tiles.Units.PlayerClasses.Warriors.WarriorTypes;

import BusinessLayer.Tiles.Units.PlayerClasses.Warriors.Warrior;

public class TheHound extends Warrior {
    private static final char TILE = '@';
    private static final String NAME = "The Hound";
    private static final int HEALTH_AMOUNT = 400;
    private static final int ATTACK_POINTS = 20;
    private static final int DEFENCE_POINTS = 6;
    private static final int ABILITY_COOLDOWN = 5;

    public TheHound() {
        super(TILE, NAME, HEALTH_AMOUNT, ATTACK_POINTS, DEFENCE_POINTS, ABILITY_COOLDOWN);
    }
}
