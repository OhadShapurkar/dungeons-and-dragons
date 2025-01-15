package Tests.Units.EnemiesTests.MonstersTests;

import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.Monster;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.WhiteWalker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class WhiteWalkerTest {
    static Monster whiteWalker;

    @BeforeAll
    static void setUp() {
        whiteWalker = new WhiteWalker();
    }

    @Test
    void initTest() {
        Assertions.assertEquals("w", whiteWalker.toString(), "White Walker toString not as expected");
        Assertions.assertEquals(2000, whiteWalker.getHealth().getMaxHealth(), "White Walker max health not as expected");
        Assertions.assertEquals(2000, whiteWalker.getHealth().getHealth(), "White Walker health not as expected");
        Assertions.assertEquals(150, whiteWalker.getAttack(), "White Walker attack points not as expected");
        Assertions.assertEquals(50, whiteWalker.getDefense(), "White Walker defense points not as expected");
        Assertions.assertEquals(6, whiteWalker.getVisionRange(), "White Walker vision range not as expected");
        Assertions.assertEquals(1000, whiteWalker.getExperienceValue(), "White Walker experience value not as expected");
    }
}