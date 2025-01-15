package Tests.Units.EnemiesTests.MonstersTests;

import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.Monster;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.QueensGuard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class QueensGuardTest {
    static Monster queensGuard;

    @BeforeAll
    static void setUp() {
        queensGuard = new QueensGuard();
    }

    @Test
    void initTest() {
        Assertions.assertEquals("q", queensGuard.toString(), "Queens Guard toString not as expected");
        Assertions.assertEquals(400, queensGuard.getHealth().getMaxHealth(), "Queens Guard max health not as expected");
        Assertions.assertEquals(400, queensGuard.getHealth().getHealth(), "Queens Guard health not as expected");
        Assertions.assertEquals(20, queensGuard.getAttack(), "Queens Guard attack points not as expected");
        Assertions.assertEquals(15, queensGuard.getDefense(), "Queens Guard defense points not as expected");
        Assertions.assertEquals(5, queensGuard.getVisionRange(), "Queens Guard vision range not as expected");
        Assertions.assertEquals(100, queensGuard.getExperienceValue(), "Queens Guard experience value not as expected");
    }
}