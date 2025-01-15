package ServiceLayer;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import BusinessLayer.Game.Game;
import BusinessLayer.Tiles.Units.PlayerClasses.Mages.MageTypes.Melisandre;
import BusinessLayer.Tiles.Units.PlayerClasses.Mages.MageTypes.ThorosOfMyr;
import BusinessLayer.Tiles.Units.PlayerClasses.Player;
import BusinessLayer.Tiles.Units.PlayerClasses.Rogues.RogueTypes.AryaStark;
import BusinessLayer.Tiles.Units.PlayerClasses.Rogues.RogueTypes.Bronn;
import BusinessLayer.Tiles.Units.PlayerClasses.Warriors.WarriorTypes.JohnSnow;
import BusinessLayer.Tiles.Units.PlayerClasses.Warriors.WarriorTypes.TheHound;
import BusinessLayer.Utils.Generators.RandomGenerator;
import ServiceLayer.View.CLI;
import BusinessLayer.Utils.Position;


public class Main {
    public static void main(String[] args) {

        // CLI initialization
        CLI cli = new CLI();
        Player[] characters = new Player[]{new JohnSnow(), new TheHound(), new Melisandre(), new ThorosOfMyr(), new AryaStark(), new Bronn()};
        System.out.println("Welcome to our game!");
        System.out.println("Here are the characters you can choose from:");
        for(int i = 0; i < characters.length; i++) {
            characters[i].initialize(null, new RandomGenerator(),null, cli.getCallback());
            System.out.println((i+1) + ":");
            System.out.println(characters[i].printStats());
            System.out.println();
        }

        // Get character index from user
        Scanner scanner = new Scanner(System.in);
        boolean chosen = false;
        char chr = '0';
        while(!chosen){
            String input = scanner.next();
            if(input.length() != 1) continue;
            chr = input.charAt(0);
            if(chr >= '1' && chr <= '6') chosen = true;
            else System.out.println("Invalid input, please choose a number between 1-6");
        }
        int characterIndex = chr - '0';
        System.out.println("Your character: " + characters[characterIndex-1].getName());
        Game game = new Game(characterIndex, new RandomGenerator(), cli.getInputCallback());
        game.addObserver(cli.getCallback());

        // Get levels directory path
        String path;
        try {path = args[0];}
        catch(Exception e) {path = "./src/ServiceLayer/levels_dir";}

        // Game initialization
        File directoryPath = new File(path);
        File[] files = directoryPath.listFiles();
        String[] filePathsArray = Arrays.stream(files)
                .filter(File::isFile) // Ensure it's a file
                .map(File::getAbsolutePath)
                .toArray(String[]::new);
        game.initGame(filePathsArray);
        game.startGame();
    }
}