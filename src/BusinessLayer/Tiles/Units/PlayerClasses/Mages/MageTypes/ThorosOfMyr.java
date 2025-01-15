package BusinessLayer.Tiles.Units.PlayerClasses.Mages.MageTypes;
import BusinessLayer.Tiles.Units.PlayerClasses.Mages.Mage;

public class ThorosOfMyr extends Mage {
    private static final char TILE = '@';
    private static final String NAME = "Thoros Of Myr";
    private static final int HEALTH_AMOUNT = 250;
    private static final int ATTACK_POINTS = 25;
    private static final int DEFENCE_POINTS = 4;
    private static final int MANA_POOL = 150;
    private static final int MANA_COST = 20;
    private static final int SPELL_POWER = 20;
    private static final int HIT_COUNTS = 3;
    private static final int ABILITY_RANGE = 4;

    public ThorosOfMyr() 
    {
        super(TILE, NAME, HEALTH_AMOUNT, ATTACK_POINTS, DEFENCE_POINTS, MANA_POOL, MANA_COST, SPELL_POWER, HIT_COUNTS, ABILITY_RANGE);
    }
}
