package Tests.Units.EnemiesTests.MonstersTests;

import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.Monster;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.Wright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class WrightTest {
    static Monster wright;

    @BeforeAll
    static void setUp() {
        wright = new Wright();
    }

    @Test
    void initTest() {
        Assertions.assertEquals("z", wright.toString(), "Wright toString not as expected");
        Assertions.assertEquals(600, wright.getHealth().getMaxHealth(), "Wright max health not as expected");
        Assertions.assertEquals(600, wright.getHealth().getHealth(), "Wright health not as expected");
        Assertions.assertEquals(30, wright.getAttack(), "Wright attack points not as expected");
        Assertions.assertEquals(15, wright.getDefense(), "Wright defense points not as expected");
        Assertions.assertEquals(3, wright.getVisionRange(), "Wright vision range not as expected");
        Assertions.assertEquals(100, wright.getExperienceValue(), "Wright experience value not as expected");
    }
}