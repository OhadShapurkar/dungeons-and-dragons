package Tests.Units.EnemiesTests.TrapsTests;

import BusinessLayer.Tiles.Units.EnemyClasses.Traps.Trap;
import BusinessLayer.Tiles.Units.EnemyClasses.Traps.TrapTypes.BonusTrap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BonusTrapTest {
    static Trap bonusTrap;

    @BeforeAll
    static void setUp() {
        bonusTrap = new BonusTrap();
    }

    @Test
    void initTest()
    {
        Assertions.assertEquals("B", bonusTrap.toString(), "Bonus trap toString not as expected");
        Assertions.assertEquals(1, bonusTrap.getHealth().getMaxHealth(), "Bonus trap max health not as expected");
        Assertions.assertEquals(1, bonusTrap.getHealth().getHealth(), "Bonus trap health not as expected");
        Assertions.assertEquals(1, bonusTrap.getAttack(), "Bonus trap attack points not as expected");
        Assertions.assertEquals(1, bonusTrap.getDefense(), "Bonus trap defense points not as expected");
        Assertions.assertEquals(250, bonusTrap.getExperienceValue(), "Bonus trap experience value not as expected");
        Assertions.assertEquals(1, bonusTrap.getVisibleTime(), "Bonus trap visibility time not as expected");
        Assertions.assertEquals(5, bonusTrap.getInvisibleTime(), "Bonus trap invisibility time not as expected");
        Assertions.assertEquals(0, bonusTrap.getTickCount(), "Bonus trap ticks count not as expected");
    }
}