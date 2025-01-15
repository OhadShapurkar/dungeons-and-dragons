package Tests.Units.EnemiesTests.TrapsTests;

import BusinessLayer.Tiles.Units.EnemyClasses.Traps.Trap;
import BusinessLayer.Tiles.Units.EnemyClasses.Traps.TrapTypes.QueensTrap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class QueensTrapTest {
    static Trap queensTrap;

    @BeforeAll
    static void setUp() {
        queensTrap = new QueensTrap();
    }

    @Test
    void initTest() {
        Assertions.assertEquals("Q", queensTrap.toString(), "Queens trap toString not as expected");
        Assertions.assertEquals(250, queensTrap.getHealth().getMaxHealth(), "Queens trap max health not as expected");
        Assertions.assertEquals(250, queensTrap.getHealth().getHealth(), "Queens trap health not as expected");
        Assertions.assertEquals(50, queensTrap.getAttack(), "Queens trap attack points not as expected");
        Assertions.assertEquals(10, queensTrap.getDefense(), "Queens trap defense points not as expected");
        Assertions.assertEquals(100, queensTrap.getExperienceValue(), "Queens trap experience value not as expected");
        Assertions.assertEquals(3, queensTrap.getVisibleTime(), "Queens trap visibility time not as expected");
        Assertions.assertEquals(7, queensTrap.getInvisibleTime(), "Queens trap invisibility time not as expected");
        Assertions.assertEquals(0, queensTrap.getTickCount(), "Queens trap ticks count not as expected");
    }
}