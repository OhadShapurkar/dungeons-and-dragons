package Tests.Units.PlayerCharactersTests;
import BusinessLayer.Tiles.Units.PlayerClasses.Rogues.RogueTypes.AryaStark;
import BusinessLayer.Game.Board;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.LannisterSoldier;
import BusinessLayer.Tiles.Units.EnemyClasses.Enemy;
import BusinessLayer.Utils.Generators.DeterministGenerator;
import BusinessLayer.Utils.Position;
import BusinessLayer.Utils.callbacks.DeathCallback;
import BusinessLayer.Utils.callbacks.MessageCallback;
import ServiceLayer.View.CLI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;

class AryaStarkTest {

        CLI cli = new CLI();
        AryaStark Arya;
        DeathCallback dc = () ->{ (new LannisterSoldier()).die(); };
        MessageCallback m = cli.getCallback();
        Board board;
        LannisterSoldier enemy;

    @BeforeEach
    void setUp() {
        Arya= new AryaStark();
        Arya.initialize(new Position(1,1), new DeterministGenerator(25), dc, m);
        enemy = new LannisterSoldier();
        enemy.initialize(new Position(7,7), new DeterministGenerator(25), dc, m);
        ArrayList<Enemy> el = new ArrayList<Enemy>();
        el.add(enemy);
        board = new Board(new ArrayList<Tile>(), Arya, el, 9);
        Arya.setGameContext(board);
    }
    @Test
    void initTest() {
        Assertions.assertEquals("@", Arya.toString(), "Arya Stark tile doesn't match");
        Assertions.assertEquals(150, Arya.getHealth().getHealth(), "Arya Stark HP doesn't match");
        Assertions.assertEquals("Arya Stark", Arya.getName(), "Arya Stark name doesn't match");
        Assertions.assertEquals(150, Arya.getHealth().getMaxHealth(), "Arya Stark Max HP doesn't match");
        Assertions.assertEquals(40, Arya.getAttack(), "Arya Stark attack doesn't match");
        Assertions.assertEquals(2, Arya.getDefense(), "Arya Stark defense doesn't match");
        Assertions.assertEquals(100, Arya.getCurrentEnergy(), "Arya Stark initial energy doesn't match");
        Assertions.assertEquals(20, Arya.getAbilityCost(), "Arya Stark ability cost doesn't match");
    }

    @Test
    void levelUpTest() {
        Arya.getHealth().decreaseHealth(70);
        Arya.levelUp();
        System.out.println(Arya.printStats());
        Assertions.assertEquals(170, Arya.getHealth().getHealth(), "Arya Stark HP doesn't heal after level up");
        Assertions.assertEquals(170, Arya.getHealth().getMaxHealth(), "Arya Stark Max HP doesn't match after level up");
        Assertions.assertEquals(54, Arya.getAttack(), "Arya Stark attack doesn't match after level up");
        Assertions.assertEquals(4, Arya.getDefense(), "Arya Stark defense doesn't match after level up");
        Assertions.assertEquals(100, Arya.getCurrentEnergy(), "Arya Stark energy doesn't reset after level up");
    }

    @Test
    void onAbilityCastTest() {
        Assertions.assertTrue(Arya.areEnoughResources(), "Arya Stark can't cast with enough resources");
        Arya.onAbilityCast();  // Casting should reduce energy by the cost
        Assertions.assertEquals(80, Arya.getCurrentEnergy(), "Arya Stark energy doesn't match after cast");
    }

    @Test
    void onGameTickTest() {
        Arya.onGameTick();  // Energy should regenerate by 10, capped at 100
        Assertions.assertEquals(100, Arya.getCurrentEnergy(), "Arya Stark energy doesn't regenerate correctly");
    }

    @Test
    void changeStatsTest() {
        Arya.getHealth().decreaseHealth(70);
        Assertions.assertEquals(80, Arya.getHealth().getHealth(), "Arya Stark HP doesn't match after decrease");
        Arya.onAbilityCast();
        Assertions.assertEquals(80, Arya.getHealth().getHealth(), "Arya Stark HP doesn't match after ability cast");
        Arya.addExperiencePoints(25);
        Assertions.assertEquals(25, Arya.getExperiencePoints(), "Arya Stark experience doesn't match after change");
        Arya.addExperiencePoints(25);
        Assertions.assertEquals(0, Arya.getExperiencePoints(), "Arya Stark experience doesn't match after level up");
        Assertions.assertEquals(2, Arya.getPlayerLevel(), "Arya Stark level doesn't match after level up");
        Assertions.assertEquals(100, Arya.getCurrentNeededXP(), "Arya Stark needed XP doesn't match after level up");
        Arya.getHealth().decreaseHealth(150);
        Assertions.assertEquals(20, Arya.getHealth().getHealth(), "Arya Stark HP doesn't match after decrease after level up");
    }
    
}