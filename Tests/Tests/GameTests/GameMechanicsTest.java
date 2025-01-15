package Tests.GameTests;

import BusinessLayer.Game.Game;
import BusinessLayer.Utils.Generators.DeterministGenerator;
import ServiceLayer.View.CLI;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;


import java.io.File;
import java.util.Arrays;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GameMechanicsTest {
    static Game game;
    static CLI cli;
    static File directoryPath;
    static File[] files;
    static String[] filePathsArray;

    @BeforeAll
    static void initGame() {
        cli = new CLI();
        game = new Game(1, new DeterministGenerator(25), cli.getInputCallback());
        game.addObserver(cli.getCallback());
        directoryPath = new File("src/ServiceLayer/levels_dir");
        files = directoryPath.listFiles();
        filePathsArray = Arrays.stream(files).filter(File::isFile).map(File::getAbsolutePath).toArray(String[]::new);
        game.initGame(filePathsArray);
        game.startGameSys();
    }

    @Test
    @Order(1)
    void levelOneTest(){
        Assertions.assertEquals(1, game.getCurrentLevel().getLevelNumber(), "After init game level isn't 1");
        try {
            for (int i = 1; i <= 4; i++) game.sysAction("d"); // Start
            for (int i = 1; i <= 3; i++) game.sysAction("w");
            for (int i = 1; i <= 2; i++) game.sysAction("d");
            for (int i = 1; i <= 5; i++) game.sysAction("w");
            for (int i = 1; i <= 11; i++) game.sysAction("s");
            for (int i = 1; i <= 7; i++) game.sysAction("d");
            for (int i = 1; i <= 7; i++) game.sysAction("w");
            for (int i = 1; i <= 2; i++) game.sysAction("d"); // After first gate
            for (int i = 1; i <= 7; i++) game.sysAction("w");
            for (int i = 1; i <= 14; i++) game.sysAction("s");
            for (int i = 1; i <= 1; i++) game.sysAction("w");
            for (int i = 1; i <= 5; i++) game.sysAction("d");
            for (int i = 1; i <= 2; i++) game.sysAction("s");
            for (int i = 1; i <= 34; i++) game.sysAction("d");
            for (int i = 1; i <= 8; i++) game.sysAction("w"); // After second gate
            for (int i = 1; i <= 15; i++) game.sysAction("a");
            for (int i = 1; i <= 11; i++) game.sysAction("d");
            for (int i = 1; i <= 8; i++) game.sysAction("s");
            for (int i = 1; i <= 16; i++) game.sysAction("d"); // End
        }
        catch(Exception e){
            Assertions.fail("Game crashed at level 1");
        }
    }

    @Test
    @Order(2)
    void levelTwoTest()
    {
        Assertions.assertEquals(2, game.getCurrentLevel().getLevelNumber(), "After finish lvl 1 game level isn't 2");
        try {
            for (int i = 1; i <= 14; i++) game.sysAction("d"); // Start
            for (int i = 1; i <= 30; i++) game.sysAction("a");
            for (int i = 1; i <= 31; i++) game.sysAction("d");
            for (int i = 1; i <= 12; i++) game.sysAction("w");
            for (int i = 1; i <= 2; i++) game.sysAction("d");
            for (int i = 1; i <= 2; i++) game.sysAction("s");
            for (int i = 1; i <= 1; i++) game.sysAction("w");
            for (int i = 1; i <= 1; i++) game.sysAction("a");// moving enemies
            for (int i = 1; i <= 14; i++) game.sysAction("d");
            for (int i = 1; i <= 18; i++) game.sysAction("a");
            for (int i = 1; i <= 14; i++) game.sysAction("d");
            for (int i = 1; i <= 14; i++) game.sysAction("s");
            for (int i = 1; i <= 15; i++) game.sysAction("w");
            for (int i = 1; i <= 18; i++) game.sysAction("a");// end
        }
        catch (Exception e) {
            Assertions.fail("Game crashed at level 2");
        }
    }

    @Test
    @Order(3)
    void levelThreeTest()
    {
        Assertions.assertEquals(3, game.getCurrentLevel().getLevelNumber(), "After finish lvl 2 game level isn't 3");
        try {
            for (int i = 1; i <= 26; i++) game.sysAction("a");
            for (int i = 1; i <= 5; i++) game.sysAction("d");
            for (int i = 1; i <= 1; i++) game.sysAction("a");
            for (int i = 1; i <= 9; i++) game.sysAction("w");
            for (int i = 1; i <= 49; i++) game.sysAction("d");
            for (int i = 1; i <= 49; i++) game.sysAction("a");
            for (int i = 1; i <= 18; i++) game.sysAction("s");
            for (int i = 1; i <= 67; i++) game.sysAction("d");
        }
        catch (Exception e)
        {
            Assertions.fail("Game crashed at level 3");
        }
    }

    @Test
    @Order(4)
    void levelFourTest()
    {
        Assertions.assertEquals(4, game.getCurrentLevel().getLevelNumber(), "After finish lvl 3 game level isn't 4");
        try{
            for (int i = 1; i <= 11; i++) game.sysAction("a");
            for (int i = 1; i <= 30; i++) game.sysAction("d");
            for (int i = 1; i <= 21; i++) game.sysAction("s");
            for (int i = 1; i <= 8; i++) game.sysAction("a");
            for (int i = 1; i <= 5; i++) game.sysAction("s");
            for (int i = 1; i <= 8; i++) game.sysAction("a");
            for (int i = 1; i <= 9; i++) game.sysAction("d");
            for (int i = 1; i <= 5; i++) game.sysAction("w");
            for (int i = 1; i <= 5; i++) game.sysAction("d");
            for (int i = 1; i <= 11; i++) game.sysAction("a");
            for (int i = 1; i <= 2; i++) game.sysAction("s");
            for (int i = 1; i <= 5; i++) game.sysAction("a");
            for (int i = 1; i <= 2; i++) game.sysAction("w");
            for (int i = 1; i <= 1; i++) game.sysAction("d");
            for (int i = 1; i <= 1; i++) game.sysAction("a");
            for (int i = 1; i <= 2; i++) game.sysAction("w");
            for (int i = 1; i <= 15; i++) game.sysAction("d");
            for (int i = 1; i <= 20; i++) game.sysAction("a");
            for (int i = 1; i <= 2; i++) game.sysAction("s");
            for (int i = 1; i <= 5; i++) game.sysAction("d");
        }
        catch(Exception e){
            Assertions.fail("Game crashed at level 4");
        }
    }
}