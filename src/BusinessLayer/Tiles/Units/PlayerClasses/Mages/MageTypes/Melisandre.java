package BusinessLayer.Tiles.Units.PlayerClasses.Mages.MageTypes;

import BusinessLayer.Tiles.Units.PlayerClasses.Mages.Mage;

public class Melisandre extends Mage {
    private static final char TILE = '@';
    private static final String NAME = "Melisandre";
    private static final int HEALTH_AMOUNT = 100;
    private static final int ATTACK_POINTS = 5;
    private static final int DEFENCE_POINTS = 1;
    private static final int MANA_POOL = 300;
    private static final int MANA_COST = 30;
    private static final int SPELL_POWER = 15;
    private static final int HIT_COUNTS = 5;
    private static final int ABILITY_RANGE = 6;

    public Melisandre() 
    {
        super(TILE, NAME, HEALTH_AMOUNT, ATTACK_POINTS, DEFENCE_POINTS, MANA_POOL, MANA_COST, SPELL_POWER, HIT_COUNTS, ABILITY_RANGE);
    }
}
