package BusinessLayer.Tiles.Units.PlayerClasses.Rogues.RogueTypes;

import BusinessLayer.Tiles.Units.PlayerClasses.Rogues.Rogue;

public class Bronn extends Rogue {
        
        private static final char TILE = '@';
        private static final String NAME = "Bronn";
        private static final int HEALTH_AMOUNT = 250;
        private static final int ATTACK_POINTS = 35;
        private static final int DEFENCE_POINTS = 3;
        private static final int MAX_ENERGY = 100;
        private static final int ABILITY_COST = 50;
    
        public Bronn() 
        {
            super(TILE, NAME, HEALTH_AMOUNT, ATTACK_POINTS, DEFENCE_POINTS, MAX_ENERGY, ABILITY_COST);
        }
}
