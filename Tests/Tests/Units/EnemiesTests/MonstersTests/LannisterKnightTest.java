package Tests.Units.EnemiesTests.MonstersTests;

import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.Monster;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.LannisterKnight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class LannisterKnightTest {
    static Monster lannisterKnight;

    @BeforeAll
    static void setUp() {
        lannisterKnight = new LannisterKnight();
    }

    @Test
    void initTest() {
        Assertions.assertEquals("k", lannisterKnight.toString(), "Lannister Knight toString not as expected");
        Assertions.assertEquals(200, lannisterKnight.getHealth().getMaxHealth(), "Lannister Knight max health not as expected");
        Assertions.assertEquals(200, lannisterKnight.getHealth().getHealth(), "Lannister Knight health not as expected");
        Assertions.assertEquals(14, lannisterKnight.getAttack(), "Lannister Knight attack points not as expected");
        Assertions.assertEquals(8, lannisterKnight.getDefense(), "Lannister Knight defense points not as expected");
        Assertions.assertEquals(4, lannisterKnight.getVisionRange(), "Lannister Knight vision range not as expected");
        Assertions.assertEquals(50, lannisterKnight.getExperienceValue(), "Lannister Knight experience value not as expected");
    }
}