package Tests.Units.EnemiesTests.MonstersTests;

import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.Monster;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.NightsKing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class NightsKingTest {
    static Monster nightsKing;

    @BeforeAll
    static void setUp() {
        nightsKing = new NightsKing();
    }

    @Test
    void initTest() {
        Assertions.assertEquals("K", nightsKing.toString(), "Nights King toString not as expected");
        Assertions.assertEquals(5000, nightsKing.getHealth().getMaxHealth(), "Nights King max health not as expected");
        Assertions.assertEquals(5000, nightsKing.getHealth().getHealth(), "Nights King health not as expected");
        Assertions.assertEquals(300, nightsKing.getAttack(), "Nights King attack points not as expected");
        Assertions.assertEquals(150, nightsKing.getDefense(), "Nights King defense points not as expected");
        Assertions.assertEquals(8, nightsKing.getVisionRange(), "Nights King vision range not as expected");
        Assertions.assertEquals(5000, nightsKing.getExperienceValue(), "Nights King experience value not as expected");
    }
}