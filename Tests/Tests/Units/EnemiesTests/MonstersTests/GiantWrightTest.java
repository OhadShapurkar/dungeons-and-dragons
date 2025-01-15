package Tests.Units.EnemiesTests.MonstersTests;

import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.Monster;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.GiantWright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GiantWrightTest {
    static Monster giantWright;

    @BeforeAll
    static void setUp() {
        giantWright = new GiantWright();
    }

    @Test
    void initTest() {
        Assertions.assertEquals("g", giantWright.toString(), "Giant Wright toString not as expected");
        Assertions.assertEquals(1500, giantWright.getHealth().getMaxHealth(), "Giant Wright max health not as expected");
        Assertions.assertEquals(1500, giantWright.getHealth().getHealth(), "Giant Wright health not as expected");
        Assertions.assertEquals(100, giantWright.getAttack(), "Giant Wright attack points not as expected");
        Assertions.assertEquals(40, giantWright.getDefense(), "Giant Wright defense points not as expected");
        Assertions.assertEquals(5, giantWright.getVisionRange(), "Giant Wright vision range not as expected");
        Assertions.assertEquals(500, giantWright.getExperienceValue(), "Giant Wright experience value not as expected");
    }
}