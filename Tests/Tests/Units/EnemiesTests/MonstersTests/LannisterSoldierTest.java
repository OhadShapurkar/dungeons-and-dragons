package Tests.Units.EnemiesTests.MonstersTests;

import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.Monster;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.LannisterSoldier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class LannisterSoldierTest {
    static Monster lannisterSoldier;

    @BeforeAll
    static void setUp() {
        lannisterSoldier = new LannisterSoldier();
    }

    @Test
    void initTest() {
        Assertions.assertEquals("s", lannisterSoldier.toString(), "Lannister Soldier toString not as expected");
        Assertions.assertEquals(80, lannisterSoldier.getHealth().getMaxHealth(), "Lannister Soldier max health not as expected");
        Assertions.assertEquals(80, lannisterSoldier.getHealth().getHealth(), "Lannister Soldier health not as expected");
        Assertions.assertEquals(8, lannisterSoldier.getAttack(), "Lannister Soldier attack points not as expected");
        Assertions.assertEquals(3, lannisterSoldier.getDefense(), "Lannister Soldier defense points not as expected");
        Assertions.assertEquals(3, lannisterSoldier.getVisionRange(), "Lannister Soldier vision range not as expected");
        Assertions.assertEquals(25, lannisterSoldier.getExperienceValue(), "Lannister Soldier experience value not as expected");
    }
}