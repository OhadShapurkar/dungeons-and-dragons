package Tests.Units.EnemiesTests.TrapsTests;

import BusinessLayer.Tiles.Units.EnemyClasses.Traps.Trap;
import BusinessLayer.Tiles.Units.EnemyClasses.Traps.TrapTypes.DeathTrap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DeathTrapTest {
    static Trap deathTrap;

    @BeforeAll
    static void setUp() {
        deathTrap = new DeathTrap();
    }

    @Test
    void initTest() {
        Assertions.assertEquals("D", deathTrap.toString(), "Death trap toString not as expected");
        Assertions.assertEquals(500, deathTrap.getHealth().getMaxHealth(), "Death trap max health not as expected");
        Assertions.assertEquals(500, deathTrap.getHealth().getHealth(), "Death trap health not as expected");
        Assertions.assertEquals(100, deathTrap.getAttack(), "Death trap attack points not as expected");
        Assertions.assertEquals(20, deathTrap.getDefense(), "Death trap defense points not as expected");
        Assertions.assertEquals(250, deathTrap.getExperienceValue(), "Death trap experience value not as expected");
        Assertions.assertEquals(1, deathTrap.getVisibleTime(), "Death trap visibility time not as expected");
        Assertions.assertEquals(10, deathTrap.getInvisibleTime(), "Death trap invisibility time not as expected");
        Assertions.assertEquals(0, deathTrap.getTickCount(), "Death trap ticks count not as expected");
    }
}