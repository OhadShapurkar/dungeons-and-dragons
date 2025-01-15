package Tests.Units.PlayerCharactersTests;

import BusinessLayer.Game.Board;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.LannisterSoldier;
import BusinessLayer.Tiles.Units.EnemyClasses.Enemy;
import BusinessLayer.Tiles.Units.PlayerClasses.Mages.MageTypes.ThorosOfMyr;
import BusinessLayer.Utils.Generators.DeterministGenerator;
import BusinessLayer.Utils.Position;
import BusinessLayer.Utils.callbacks.DeathCallback;
import BusinessLayer.Utils.callbacks.MessageCallback;
import ServiceLayer.View.CLI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;

class ThorosOfMyrTest {

    CLI cli = new CLI();
    ThorosOfMyr ThorosOfMyr;
    DeathCallback dc = () -> { System.out.println("Enemy died"); };
    MessageCallback m = message -> System.out.println(message);
    Board board;
    LannisterSoldier enemy, anotherEnemy;

    @BeforeEach
    void setUp() {
        ThorosOfMyr = new ThorosOfMyr();
        ThorosOfMyr.initialize(new Position(1,1), new DeterministGenerator(25), dc, m);
        enemy = new LannisterSoldier();
        enemy.initialize(new Position(2,2), new DeterministGenerator(25), dc, m);  // Enemy within ability range
        anotherEnemy = new LannisterSoldier();
        anotherEnemy.initialize(new Position(1,3), new DeterministGenerator(25), dc, m);  // Second enemy within range
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);
        enemies.add(anotherEnemy);
        board = new Board(new ArrayList<Tile>(), ThorosOfMyr, enemies, 9);
        ThorosOfMyr.setGameContext(board);
    }

    @Test
    void initTest() {
        Assertions.assertEquals("@", ThorosOfMyr.toString(), "Thoros Of Myr tile doesn't match");
        Assertions.assertEquals("Thoros Of Myr", ThorosOfMyr.getName(), "Thoros Of Myr name doesn't match");
        Assertions.assertEquals(250, ThorosOfMyr.getHealth().getMaxHealth(), "Thoros Of Myr Max HP doesn't match");
        Assertions.assertEquals(250, ThorosOfMyr.getHealth().getHealth(), "Thoros Of Myr initial health doesn't match");
        Assertions.assertEquals(150, ThorosOfMyr.getManaPool(), "Thoros Of Myr Mana Pool doesn't match");
        Assertions.assertEquals(37, ThorosOfMyr.getCurrentMana(), "Thoros Of Myr initial mana doesn't match 1/4th of Mana Pool");
        Assertions.assertEquals(20, ThorosOfMyr.getSpellPower(), "Thoros Of Myr Spell Power doesn't match");
        Assertions.assertEquals(4, ThorosOfMyr.getAbilityRange(), "Thoros Of Myr Ability Range doesn't match");
        Assertions.assertEquals(3, ThorosOfMyr.getHitCounts(), "Thoros Of Myr Hit Counts doesn't match");
        Assertions.assertEquals(20, ThorosOfMyr.getManaCost(), "Thoros Of Myr Mana Cost doesn't match");
        Assertions.assertEquals(4, ThorosOfMyr.getDefense(), "Thoros Of Myr Defense doesn't match");
        Assertions.assertEquals(25, ThorosOfMyr.getAttack(), "Thoros Of Myr Attack doesn't match");
    }

    @Test
    void abilityCastTest() {
        // Ensure enough mana to cast
        Assertions.assertTrue(ThorosOfMyr.areEnoughResources(), "Thoros Of Myr should have enough mana to cast");
        ThorosOfMyr.onAbilityCast();
        Assertions.assertEquals(17, ThorosOfMyr.getCurrentMana(), "Thoros Of Myr mana should decrease by mana cost after casting");
        Assertions.assertNotEquals(enemy.getHealth().getHealth(), enemy.getHealth().getMaxHealth(), "Enemy health should be reduced after casting ability");
    }

    @Test
    void manaRegenerationTest() {
        ThorosOfMyr.setCurrentMana(30); // Use some mana
        ThorosOfMyr.onGameTick(); // Simulate a game tick for mana regeneration
        Assertions.assertTrue(ThorosOfMyr.getCurrentMana() > 7, "Thoros Of Myr mana should regenerate after a game tick");
    }

    @Test
    void levelUpEffectsTest() {
        ThorosOfMyr.addExperiencePoints(500); // Assumed enough to level up
        ThorosOfMyr.levelUp();
        Assertions.assertEquals(6, ThorosOfMyr.getPlayerLevel(), "Thoros Of Myr level should increase on level up");
        Assertions.assertEquals(650, ThorosOfMyr.getManaPool(), "Thoros Of Myr Mana Pool should increase on level up");
        Assertions.assertEquals(535, ThorosOfMyr.getCurrentMana(), "Thoros Of Myr Current Mana should be updated to new Mana Pool on level up");
        Assertions.assertEquals(220, ThorosOfMyr.getSpellPower(), "Thoros Of Myr Spell Power should increase on level up");
    }
}
