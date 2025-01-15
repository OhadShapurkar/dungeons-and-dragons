package Tests.Units.PlayerCharactersTests;

import BusinessLayer.Game.Board;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.LannisterSoldier;
import BusinessLayer.Tiles.Units.EnemyClasses.Enemy;
import BusinessLayer.Tiles.Units.PlayerClasses.Warriors.WarriorTypes.TheHound;
import BusinessLayer.Utils.Generators.DeterministGenerator;
import BusinessLayer.Utils.Position;
import BusinessLayer.Utils.callbacks.DeathCallback;
import BusinessLayer.Utils.callbacks.MessageCallback;
import ServiceLayer.View.CLI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;

class TheHoundTest {

    CLI cli = new CLI();
    TheHound TheHound;
    DeathCallback dc = () -> { System.out.println("Enemy died"); };
    MessageCallback m = message -> System.out.println(message);
    Board board;
    LannisterSoldier enemy;

    @BeforeEach
    void setUp() {
        TheHound = new TheHound();
        TheHound.initialize(new Position(1,1), new DeterministGenerator(25), dc, m);
        enemy = new LannisterSoldier();
        enemy.initialize(new Position(2,2), new DeterministGenerator(25), dc, m); // Enemy within ability range
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);
        board = new Board(new ArrayList<Tile>(), TheHound, enemies, 9);
        TheHound.setGameContext(board);
    }

    @Test
    void initTest() {
        Assertions.assertEquals("@", TheHound.toString(), "The Hound toString doesn't match expected value");
        Assertions.assertEquals("The Hound", TheHound.getName(), "The Hound name doesn't match");
        Assertions.assertEquals(400, TheHound.getHealth().getMaxHealth(), "The Hound initial health doesn't match");
        Assertions.assertEquals(400, TheHound.getHealth().getHealth(), "The Hound initial health doesn't match");
        Assertions.assertEquals(20, TheHound.getAttack(), "The Hound attack doesn't match");
        Assertions.assertEquals(6, TheHound.getDefense(), "The Hound defense doesn't match");
        Assertions.assertEquals(5, TheHound.getAbilityCooldown(), "The Hound ability cooldown doesn't match");
        Assertions.assertEquals(0, TheHound.getRemainingCooldown(), "The Hound initial cooldown should be zero");
        Assertions.assertEquals(1, TheHound.getPlayerLevel(), "The Hound level doesn't match");
        Assertions.assertEquals(0, TheHound.getExperiencePoints(), "The Hound experience doesn't match");
        Assertions.assertEquals(50, TheHound.getCurrentNeededXP(), "The Hound needed XP doesn't match");
    }

    @Test
    void abilityUseTest() {
        TheHound.getHealth().decreaseHealth(70);
        TheHound.onAbilityCast(); // First cast should work
        TheHound.onGameTick();
        Assertions.assertEquals(5, TheHound.getRemainingCooldown(), "The Hound cooldown after casting doesn't match ability cooldown");
        Assertions.assertTrue(TheHound.getHealth().getHealth() == 390, "The Hound should have healed");
        TheHound.onAbilityCast(); TheHound.onGameTick();
        Assertions.assertEquals(4, TheHound.getRemainingCooldown(), "The Hound cooldown doesn't match after cast");
        Assertions.assertFalse(TheHound.areEnoughResources(), "The Hound can cast without enough resources");
        TheHound.onAbilityCast(); TheHound.onGameTick();
        Assertions.assertEquals(3, TheHound.getRemainingCooldown(), "The Hound cooldown doesn't match after cast");
        Assertions.assertFalse(TheHound.areEnoughResources(), "The Hound can cast without enough resources");
        TheHound.onAbilityCast(); TheHound.onGameTick();
        Assertions.assertEquals(2, TheHound.getRemainingCooldown(), "The Hound cooldown doesn't match after cast");
        Assertions.assertFalse(TheHound.areEnoughResources(), "The Hound can cast without enough resources");
        TheHound.onAbilityCast(); TheHound.onGameTick();
        Assertions.assertEquals(1, TheHound.getRemainingCooldown(), "The Hound cooldown doesn't match after cast");
        Assertions.assertFalse(TheHound.areEnoughResources(), "The Hound can cast without enough resources");
        TheHound.onAbilityCast(); TheHound.onGameTick();
        Assertions.assertEquals(0, TheHound.getRemainingCooldown(), "The Hound cooldown doesn't match after cast");
        Assertions.assertTrue(TheHound.areEnoughResources(), "The Hound can't cast with enough resources");
        // Trying to cast again should fail due to cooldown
        TheHound.onAbilityCast(); TheHound.onGameTick();
        Assertions.assertEquals(5, TheHound.getRemainingCooldown(), "Cooldown should not reset if ability is cast while already on cooldown");
    }

    @Test
    void onGameTickCooldownReductionTest() {
        TheHound.onAbilityCast();
        for (int i = 0; i < 6; i++) {
            TheHound.onGameTick();
        }
        Assertions.assertEquals(0, TheHound.getRemainingCooldown(), "Cooldown should decrement properly over game ticks");
    }

    @Test
    void levelUpTest() {
        TheHound.addExperiencePoints(1000); // Assume this is enough for a level up
        TheHound.levelUp();
        Assertions.assertEquals(0, TheHound.getRemainingCooldown(), "The Hound Cooldown should reset after leveling up");
        Assertions.assertEquals(7, TheHound.getPlayerLevel(), "The Hound Level up does not increase player level as expected");
        Assertions.assertEquals(805, TheHound.getHealth().getMaxHealth(), "The Hound Health increase on level up does not match expected value");
        Assertions.assertEquals(805, TheHound.getHealth().getHealth(), "The Hound Health increase on level up does not match expected value");
        Assertions.assertEquals(182, TheHound.getAttack(), "The Hound Attack increase on level up does not match expected value");
        Assertions.assertEquals(60, TheHound.getDefense(), "The Hound Defense increase on level up does not match expected value");
        Assertions.assertEquals(5, TheHound.getAbilityCooldown(), "The Hound Ability cooldown decrease on level up does not match expected value");
    }
}
