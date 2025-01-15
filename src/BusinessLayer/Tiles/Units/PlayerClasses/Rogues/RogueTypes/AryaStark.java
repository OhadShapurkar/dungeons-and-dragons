package BusinessLayer.Tiles.Units.PlayerClasses.Rogues.RogueTypes;


import BusinessLayer.Tiles.Units.PlayerClasses.Rogues.Rogue;

public class AryaStark extends Rogue {
    
    private static final char TILE = '@';
    private static final String NAME = "Arya Stark";
    private static final int HEALTH_AMOUNT = 150;
    private static final int ATTACK_POINTS = 40;
    private static final int DEFENCE_POINTS = 2;
    private static final int MAX_ENERGY = 100;
    private static final int ABILITY_COST = 20;

    public AryaStark() 
    {
        super(TILE, NAME, HEALTH_AMOUNT, ATTACK_POINTS, DEFENCE_POINTS, MAX_ENERGY, ABILITY_COST);
    }
}
