package Tests.Units.EnemiesTests.MonstersTests;

import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.Monster;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.TheMountain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TheMountainTest {
    static Monster theMountain;

    @BeforeAll
    static void setUp() {
        theMountain = new TheMountain();
    }

    @Test
    void initTest() {
        Assertions.assertEquals("M", theMountain.toString(), "The Mountain toString not as expected");
        Assertions.assertEquals(1000, theMountain.getHealth().getMaxHealth(), "The Mountain max health not as expected");
        Assertions.assertEquals(1000, theMountain.getHealth().getHealth(), "The Mountain health not as expected");
        Assertions.assertEquals(60, theMountain.getAttack(), "The Mountain attack points not as expected");
        Assertions.assertEquals(25, theMountain.getDefense(), "The Mountain defense points not as expected");
        Assertions.assertEquals(6, theMountain.getVisionRange(), "The Mountain vision range not as expected");
        Assertions.assertEquals(500, theMountain.getExperienceValue(), "The Mountain experience value not as expected");
    }
}