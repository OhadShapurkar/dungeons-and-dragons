package Tests.Units.PlayerCharactersTests;

import BusinessLayer.Game.Board;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Units.EnemyClasses.Monsters.MonsterType.LannisterSoldier;
import BusinessLayer.Tiles.Units.EnemyClasses.Enemy;
import BusinessLayer.Tiles.Units.PlayerClasses.Rogues.RogueTypes.Bronn;
import BusinessLayer.Utils.Generators.DeterministGenerator;
import BusinessLayer.Utils.Position;
import BusinessLayer.Utils.callbacks.DeathCallback;
import BusinessLayer.Utils.callbacks.MessageCallback;
import ServiceLayer.View.CLI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;

class BronnTest {

    CLI cli = new CLI();
    Bronn Bronn;
    DeathCallback dc = () -> { (new LannisterSoldier()).die(); };
    MessageCallback m = cli.getCallback();
    Board board;
    LannisterSoldier enemy, anotherEnemy;
    ArrayList<Enemy> enemies;

    @BeforeEach
    void setUp() {
        Bronn = new Bronn();  // Assuming the special ability cost is 50
        Bronn.initialize(new Position(1,1), new DeterministGenerator(25), dc, m);
        enemy = new LannisterSoldier();
        enemy.initialize(new Position(7,7), new DeterministGenerator(25), dc, m);
        anotherEnemy = new LannisterSoldier();
        anotherEnemy.initialize(new Position(1,2), new DeterministGenerator(25), dc, m);
        enemies = new ArrayList<>();
        enemies.add(enemy);
        enemies.add(anotherEnemy);
        board = new Board(new ArrayList<Tile>(), Bronn, enemies, 9);
        Bronn.setGameContext(board);
    }

    @Test
    void initTest() {
        Assertions.assertEquals("@", Bronn.toString(), "Bronn tile doesn't match");
        Assertions.assertEquals(250, Bronn.getHealth().getHealth(), "Bronn HP doesn't match");
        Assertions.assertEquals("Bronn", Bronn.getName(), "Bronn name doesn't match");
        Assertions.assertEquals(250, Bronn.getHealth().getMaxHealth(), "Bronn Max HP doesn't match");
        Assertions.assertEquals(35, Bronn.getAttack(), "Bronn attack doesn't match");
        Assertions.assertEquals(3, Bronn.getDefense(), "Bronn defense doesn't match");
        Assertions.assertEquals(100, Bronn.getCurrentEnergy(), "Bronn initial energy doesn't match");
        Assertions.assertEquals(50, Bronn.getAbilityCost(), "Bronn ability cost doesn't match");
    }

    @Test
    void onAbilityCastTest() {
        Assertions.assertTrue(Bronn.areEnoughResources(), "Bronn can't cast with enough resources before cast");
        Bronn.onAbilityCast();
        Assertions.assertEquals(50, Bronn.getCurrentEnergy(), "Bronn energy doesn't match after cast");
        // Test the effect on nearby enemy
        Assertions.assertTrue(anotherEnemy.getHealth().getHealth() < anotherEnemy.getHealth().getMaxHealth(), "Enemy health not affected after Bronn's ability cast");
    }

    @Test
    void onGameTickTest() {
        int initialEnergy = Bronn.getCurrentEnergy();
        Bronn.onGameTick();  // Energy should regenerate by 10, capped at 100
        Assertions.assertEquals(Math.min(initialEnergy + 10, 100), Bronn.getCurrentEnergy(), "Bronn energy doesn't regenerate correctly on game tick");
    }

    @Test
    void multipleGameTicksTest() {
        for (int i = 0; i < 5; i++) {
            Bronn.onGameTick();
        }
        Assertions.assertEquals(100, Bronn.getCurrentEnergy(), "Bronn energy doesn't regenerate correctly after multiple game ticks");
        Bronn.onAbilityCast();
        Assertions.assertEquals(50, Bronn.getCurrentEnergy(), "Bronn energy doesn't reset correctly after multiple game ticks and cast");
    }

    @Test
    void levelUpTest() {
        Bronn.addExperiencePoints(300);  // Assume enough XP for level up
        Assertions.assertEquals(4, Bronn.getPlayerLevel(), "Bronn level doesn't match after level up");
        Assertions.assertEquals(0, Bronn.getExperiencePoints(), "Bronn experience doesn't reset after level up");
        Assertions.assertEquals(340, Bronn.getHealth().getHealth(), "Bronn HP doesn't match after level up");
        Assertions.assertEquals(98, Bronn.getAttack(), "Bronn attack doesn't match after level up");
        Assertions.assertEquals(12, Bronn.getDefense(), "Bronn defense doesn't match after level up");
        Assertions.assertEquals(100, Bronn.getCurrentEnergy(), "Bronn energy doesn't reset to 100 after level up");
    }
}
