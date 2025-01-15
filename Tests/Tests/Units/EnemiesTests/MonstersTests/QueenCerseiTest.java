package Tests.Units.EnemiesTests.MonstersTests;

import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.Monster;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.QueenCersei;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class QueenCerseiTest {
    static Monster queenCersei;

    @BeforeAll
    static void setUp() {
        queenCersei = new QueenCersei();
    }

    @Test
    void initTest() {
        Assertions.assertEquals("C", queenCersei.toString(), "Queen Cersei toString not as expected");
        Assertions.assertEquals(100, queenCersei.getHealth().getMaxHealth(), "Queen Cersei max health not as expected");
        Assertions.assertEquals(100, queenCersei.getHealth().getHealth(), "Queen Cersei health not as expected");
        Assertions.assertEquals(10, queenCersei.getAttack(), "Queen Cersei attack points not as expected");
        Assertions.assertEquals(10, queenCersei.getDefense(), "Queen Cersei defense points not as expected");
        Assertions.assertEquals(1, queenCersei.getVisionRange(), "Queen Cersei vision range not as expected");
        Assertions.assertEquals(1000, queenCersei.getExperienceValue(), "Queen Cersei experience value not as expected");
    }
}