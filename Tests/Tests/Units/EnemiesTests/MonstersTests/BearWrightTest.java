package Tests.Units.EnemiesTests.MonstersTests;

import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.Monster;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.BearWright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BearWrightTest {
    static Monster bearWright;

    @BeforeAll
    static void setUp() {
        bearWright = new BearWright();
    }

    @Test
    void initTest() {
        Assertions.assertEquals("b", bearWright.toString(), "Bear Wright toString not as expected");
        Assertions.assertEquals(1000, bearWright.getHealth().getMaxHealth(), "Bear Wright max health not as expected");
        Assertions.assertEquals(1000, bearWright.getHealth().getHealth(), "Bear Wright health not as expected");
        Assertions.assertEquals(75, bearWright.getAttack(), "Bear Wright attack points not as expected");
        Assertions.assertEquals(30, bearWright.getDefense(), "Bear Wright defense points not as expected");
        Assertions.assertEquals(4, bearWright.getVisionRange(), "Bear Wright vision range not as expected");
        Assertions.assertEquals(250, bearWright.getExperienceValue(), "Bear Wright experience value not as expected");
    }
}