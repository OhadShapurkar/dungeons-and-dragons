package BusinessLayer.Tiles.Units.PlayerClasses.Warriors.WarriorTypes;

import BusinessLayer.Tiles.Units.PlayerClasses.Warriors.Warrior;

public class JohnSnow extends Warrior {
    private static final char TILE = '@';
    private static final String NAME = "John Snow";
    private static final int HEALTH_AMOUNT = 300;
    private static final int ATTACK_POINTS = 30;
    private static final int DEFENCE_POINTS = 4;
    private static final int ABILITY_COOLDOWN = 3;

    public JohnSnow() {
        super(TILE, NAME, HEALTH_AMOUNT, ATTACK_POINTS, DEFENCE_POINTS, ABILITY_COOLDOWN);
    }
}
