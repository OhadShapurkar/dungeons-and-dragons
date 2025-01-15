package Tests.Units.PlayerCharactersTests;

import BusinessLayer.Game.Board;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.LannisterSoldier;
import BusinessLayer.Tiles.Units.EnemyClasses.Enemy;
import BusinessLayer.Tiles.Units.PlayerClasses.Warriors.WarriorTypes.JohnSnow;
import BusinessLayer.Utils.Generators.DeterministGenerator;
import BusinessLayer.Utils.Position;
import BusinessLayer.Utils.callbacks.DeathCallback;
import BusinessLayer.Utils.callbacks.MessageCallback;
import ServiceLayer.View.CLI;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;

class JohnSnowTest {

    CLI cli = new CLI();
    JohnSnow John;
    DeathCallback dc = () ->{ (new LannisterSoldier()).die(); };
    MessageCallback m = cli.getCallback();
    Board board;
    LannisterSoldier enemy;


    @BeforeEach
    void setUp() {
        John = new JohnSnow();
        John.initialize(new Position(1,1), new DeterministGenerator(25), dc, m);
        enemy = new LannisterSoldier();
        enemy.initialize(new Position(7,7), new DeterministGenerator(25), dc, m);
        ArrayList<Enemy> el = new ArrayList<Enemy>();
        el.add(enemy);
        board = new Board(new ArrayList<Tile>(), John, el, 9);
        John.setGameContext(board);
    }

    @Test
    void initTest()
    {
        Assertions.assertEquals("@", John.toString(), "John Snow tile doesn't match");
        Assertions.assertEquals(300, John.getHealth().getHealth(), "John Snow HP doesn't match");
        Assertions.assertEquals("John Snow", John.getName(), "John Snow name doesn't match");
        Assertions.assertEquals(300, John.getHealth().getMaxHealth(), "John Snow Max HP doesn't match");
        Assertions.assertEquals(30, John.getAttack(), "John Snow attack doesn't match");
        Assertions.assertEquals(4, John.getDefense(), "John Snow defense doesn't match");
        Assertions.assertEquals(3, John.getAbilityCooldown(), "John Snow max cooldown doesn't match");
        Assertions.assertEquals(0, John.getRemainingCooldown(), "John Snow corrent cooldown not 0");
        Assertions.assertEquals(1, John.getPlayerLevel(), "John Snow level doesn't match");
        Assertions.assertEquals(0, John.getExperiencePoints(), "John Snow experience doesn't match");
        Assertions.assertEquals(50, John.getCurrentNeededXP(), "John Snow needed XP doesn't match");
    }

    @Test
    void levelUpTest()
    {
        John.getHealth().decreaseHealth(70);
        John.levelUp();
        Assertions.assertEquals(330, John.getHealth().getHealth(), "John Snow HP doesn't heal after level up");
        Assertions.assertEquals(330, John.getHealth().getMaxHealth(), "John Snow Max HP doesn't match after level up");
        Assertions.assertEquals(42, John.getAttack(), "John Snow attack doesn't match after level up");
        Assertions.assertEquals(8, John.getDefense(), "John Snow defense doesn't match after level up");
        Assertions.assertEquals(3, John.getAbilityCooldown(), "John Snow max cooldown doesn't match after level up");
        Assertions.assertEquals(0, John.getRemainingCooldown(), "John Snow corrent cooldown not 0 after level up");
        Assertions.assertEquals(2, John.getPlayerLevel(), "John Snow level doesn't match after level up");
        Assertions.assertEquals(0, John.getExperiencePoints(), "John Snow experience doesn't match after level up");
        Assertions.assertEquals(100, John.getCurrentNeededXP(), "John Snow needed XP doesn't match after level up");
    }

    @Test
    void onAbilityCastTest()
    {
        Assertions.assertTrue(John.areEnoughResources(), "John Snow can't cast with enough resources");
        Assertions.assertEquals(0, John.getRemainingCooldown(), "John Snow cooldown doesn't match before cast");
        John.onAbilityCast();
        John.onGameTick();
        Assertions.assertEquals(3, John.getRemainingCooldown(), "John Snow cooldown doesn't match after cast");
        Assertions.assertFalse(John.areEnoughResources(), "John Snow can cast without enough resources");
        John.onGameTick();
        Assertions.assertEquals(2, John.getRemainingCooldown(), "John Snow cooldown doesn't match after tick");
        Assertions.assertFalse(John.areEnoughResources(), "John Snow can cast without enough resources");
        John.onGameTick();
        Assertions.assertEquals(1, John.getRemainingCooldown(), "John Snow cooldown doesn't match after tick");
        Assertions.assertFalse(John.areEnoughResources(), "John Snow can cast without enough resources");
        John.onGameTick();
        Assertions.assertEquals(0, John.getRemainingCooldown(), "John Snow cooldown doesn't match after tick");
        Assertions.assertTrue(John.areEnoughResources(), "John Snow can't cast with enough resources");
        John.onGameTick();
        Assertions.assertEquals(0, John.getRemainingCooldown(), "John Snow cooldown doesn't match after tick");
        Assertions.assertTrue(John.areEnoughResources(), "John Snow can't cast with enough resources");
    }

    @Test
    void changeStatsTest()
    {
        John.getHealth().decreaseHealth(70);
        Assertions.assertEquals(230, John.getHealth().getHealth(), "John Snow HP doesn't match after decrease");
        John.abilityCast();
        Assertions.assertEquals(270, John.getHealth().getHealth(), "John Snow HP doesn't match after decrease and ability");
        John.addExperiencePoints(25);
        Assertions.assertEquals(25, John.getExperiencePoints(), "John Snow experience doesn't match after change");
        John.addExperiencePoints(25);
        Assertions.assertEquals(0, John.getExperiencePoints(), "John Snow experience doesn't match after level up");
        Assertions.assertEquals(2, John.getPlayerLevel(), "John Snow level doesn't match after level up");
        Assertions.assertEquals(100, John.getCurrentNeededXP(), "John Snow needed XP doesn't match after level up");
        John.getHealth().decreaseHealth(150);
        Assertions.assertEquals(180, John.getHealth().getHealth(), "John Snow HP doesn't match after decrease after level up");
        John.onGameTick();John.onGameTick();John.onGameTick();
        John.abilityCast();
        Assertions.assertEquals(260, John.getHealth().getHealth(), "John Snow HP doesn't match after ability after level up");
    }
}