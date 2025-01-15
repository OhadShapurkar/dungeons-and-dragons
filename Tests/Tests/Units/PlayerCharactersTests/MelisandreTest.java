package Tests.Units.PlayerCharactersTests;

import BusinessLayer.Game.Board;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.LannisterSoldier;
import BusinessLayer.Tiles.Units.EnemyClasses.Enemy;
import BusinessLayer.Tiles.Units.PlayerClasses.Mages.MageTypes.Melisandre;
import BusinessLayer.Utils.Generators.DeterministGenerator;
import BusinessLayer.Utils.Position;
import BusinessLayer.Utils.callbacks.DeathCallback;
import BusinessLayer.Utils.callbacks.MessageCallback;
import ServiceLayer.View.CLI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;

class MelisandreTest {

    CLI cli = new CLI();
    Melisandre Melisandre;
    DeathCallback dc = () -> { System.out.println("Enemy died"); };
    MessageCallback m = message -> System.out.println(message);
    Board board;
    LannisterSoldier enemy, secondEnemy;

    @BeforeEach
    void setUp() {
        Melisandre = new Melisandre();
        Melisandre.initialize(new Position(1,1), new DeterministGenerator(25), dc, m);
        enemy = new LannisterSoldier();
        enemy.initialize(new Position(1,2), new DeterministGenerator(25), dc, m);  // within ability range
        secondEnemy = new LannisterSoldier();
        secondEnemy.initialize(new Position(2,1), new DeterministGenerator(25), dc, m);  // within ability range
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);
        enemies.add(secondEnemy);
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(Melisandre); tiles.add(enemy); tiles.add(secondEnemy);
        board = new Board(tiles, Melisandre, enemies, 9);
        Melisandre.setGameContext(board);
    }

    @Test
    void initTest() {
        Assertions.assertEquals("@", Melisandre.toString(), "Melisandre tile doesn't match");
        Assertions.assertEquals("Melisandre", Melisandre.getName(), "Melisandre name doesn't match");
        Assertions.assertEquals(100, Melisandre.getHealth().getMaxHealth(), "Melisandre Max HP doesn't match");
        Assertions.assertEquals(100, Melisandre.getHealth().getHealth(), "Melisandre initial health doesn't match");
        Assertions.assertEquals(300, Melisandre.getManaPool(), "Melisandre Mana Pool doesn't match");
        Assertions.assertEquals(75, Melisandre.getCurrentMana(), "Melisandre initial mana doesn't match 1/4th of Mana Pool");
        Assertions.assertEquals(15, Melisandre.getSpellPower(), "Melisandre Spell Power doesn't match");
        Assertions.assertEquals(6, Melisandre.getAbilityRange(), "Melisandre Ability Range doesn't match");
        Assertions.assertEquals(5, Melisandre.getHitCounts(), "Melisandre Hit Counts doesn't match");
        Assertions.assertEquals(30, Melisandre.getManaCost(), "Melisandre Mana Cost doesn't match");
        Assertions.assertEquals(1, Melisandre.getDefense(), "Melisandre Defense doesn't match");
        Assertions.assertEquals(5, Melisandre.getAttack(), "Melisandre Attack doesn't match");
    }

    @Test
    void abilityCastAndEffectTest() {
        // Casting the ability should reduce mana and affect enemies within the ability range
        Melisandre.onAbilityCast();
        Assertions.assertEquals(45, Melisandre.getCurrentMana(), "Melisandre mana after casting doesn't match");
        Assertions.assertTrue(enemy.getHealth().getHealth() < enemy.getHealth().getMaxHealth(), "First enemy not affected by spell");
        Assertions.assertTrue(secondEnemy.getHealth().getHealth() < secondEnemy.getHealth().getMaxHealth(), "Second enemy not affected by spell");
    }

    @Test
    void manaRegenerationTest() {
        // Test mana regeneration over multiple game ticks
        Melisandre.onGameTick();
        Assertions.assertEquals(76, Melisandre.getCurrentMana(), "Melisandre mana after one tick doesn't match");
        Melisandre.onGameTick();
        Assertions.assertEquals(77, Melisandre.getCurrentMana(), "Melisandre mana after two ticks doesn't match");
    }

    @Test
    void levelUpTest() {
        // Assume that experience points can be directly set for testing
        // If direct setting is not possible, this needs to be adjusted according to the actual game mechanics
        Melisandre.addExperiencePoints(500); // Set enough experience to ensure a level up, adjust based on your actual level up thresholds

        int previousLevel = Melisandre.getPlayerLevel();
        int expectedManaIncrease = 25 * previousLevel; // Calculate expected mana pool increase
        int expectedSpellPowerIncrease = 10 * previousLevel; // Calculate expected spell power increase

        Melisandre.setCurrentMana(400);

        Melisandre.levelUp(); // Level up the character

        Assertions.assertEquals(6 , Melisandre.getPlayerLevel(), "Melisandre level after leveling up doesn't match");
        Assertions.assertEquals(800, Melisandre.getManaPool(), "Melisandre Mana Pool after leveling doesn't match");
        Assertions.assertEquals(600, Melisandre.getCurrentMana(), "Melisandre Current Mana after leveling doesn't match");
        Assertions.assertEquals(215, Melisandre.getSpellPower(), "Melisandre Spell Power after leveling doesn't match");

        Melisandre.setCurrentMana(750);
        Melisandre.levelUp();
        Assertions.assertEquals(975, Melisandre.getCurrentMana(), "Melisandre Current Mana after leveling doesn't match");
    }

}
